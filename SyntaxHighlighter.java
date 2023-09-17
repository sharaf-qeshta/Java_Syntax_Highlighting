import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;


public class SyntaxHighlighter
{
    public static final String[] KEYWORDS_ARRAY = {"abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto",
            "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile",
            "while", "true", "false", "null"};

    public static final HashSet<String> KEYWORDS = new HashSet<>(Arrays.asList(KEYWORDS_ARRAY));

    public static void main(String[] args) throws FileNotFoundException
    {
        if (args.length < 2)
        {
            System.out.println("Wrong Parameters");
            System.exit(-1);
        }

        // read from args[0].java
        Scanner reader = new Scanner(new File(args[0]));
        ArrayList<String> lines = new ArrayList<>();
        while (reader.hasNext())
            lines.add(reader.nextLine());
        reader.close();

        // write to args[1].html
        PrintWriter writer = new PrintWriter(new File(args[1]));
        for (String line: lines)
        {
            line = clear(line);
            if (line.trim().startsWith("//") || line.trim().startsWith("*") || line.trim().startsWith("/*"))
                writer.write("<h1 style='color: green'>" + line + "</h1>");
            else
            {
                String[] words = line.split(" ");
                writer.write("<div style='display:flex; flex-direction: row;'>");
                for (String word: words)
                {
                    if (KEYWORDS.contains(word))
                        writer.write("<h1 style='color: #000080'>" + word + "&nbsp;" + "</h1> ");
                    else
                        writer.write("<h1 style='color: blue'>" + word + "&nbsp;" + "</h1> ");
                }
                writer.write("</div>");
            }
        }
        writer.close();
    }

    public static String clear(String string)
    {
        if (string.contains(">"))
            string = string.replace(">", "&gt;");
        if (string.contains("<"))
            string = string.replace("<", "&lt;");
        if (string.contains("'"))
            string = string.replace("'", "&#039;");
        if (string.contains("\""))
            string = string.replace("'", "&quot;");
        return string;
    }
}

class Test
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Problem$05.main(new String[]
                {"JavaFile.java", "HTMLFile.html"});
    }
}
