import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket;
            //handle starting file with link
            if(markdown.charAt(0) == ('[') && currentIndex == 0){
                openBracket = 0;
            } else {
                openBracket = markdown.indexOf("[", currentIndex);

            }
            //handle images
            //if next open bracket is part of an image,
            if(openBracket>0 && markdown.charAt(openBracket-1) == '!'){
                //skip to next ')' character
                if(foreverLoop(markdown, currentIndex)){
                    break;
                }
                currentIndex = markdown.indexOf(")", currentIndex);
                continue;
            }
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            //handle errors of forever loop with empty lines at the end
            if(foreverLoop(markdown, currentIndex)){
                break;
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
            

        }

        return toReturn;
    }

    //mine: test-files/12.md
        //[\!\"\#\$\%\&\'\(\]

    //theirs: test-files/12.md
        //[]


    //mine: test-files/22.md
        //[[/bar\*"ti\*tle"]]

    //theirs: test-files/22.md
        //[]
    private static boolean foreverLoop(String markdown, int currentIndex){
        if(markdown.indexOf("[", currentIndex) == -1
            || markdown.indexOf("]", currentIndex) == -1
            || markdown.indexOf("(", currentIndex) == -1
            || markdown.indexOf(")", currentIndex) == -1
        ){
            return true;
        } 
        return false;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
