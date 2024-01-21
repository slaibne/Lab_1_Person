import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

/**
 *
 * @author wulft
 *
 * Demonstrates how to use Java NIO, a thread safe File IO library
 * to write a text file
 */
public class PersonGenerator
{


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {



        // Test data the lines of the file we will write
        Boolean response = true;
        Scanner in = new Scanner(System.in);
        ArrayList <Person>recs = new ArrayList<>();
        String IDNum;
        String firstName;
        String lastName;
        String title;
        int YOB;

        while(response)
        {
            IDNum = SafeInput.getNonZeroLenString(in,"What is this person's ID Number?");
            firstName = SafeInput.getNonZeroLenString(in,"What is this person's first name?");
            lastName = SafeInput.getNonZeroLenString(in,"What is this person's last name?");
            title = SafeInput.getNonZeroLenString(in,"What is this person's title or prefix?");
            YOB = SafeInput.getRangedInt(in,"What is this person's Year of Birth?", 1940, 2024);

            recs.add(new Person(IDNum,firstName,lastName,title,YOB));
            response = SafeInput.getYNConfirm(in, "Do you have more people?");
        }

        // use the toolkit to get the current working directory of the IDE
        // will create the file within the Netbeans project src folder
        // (may need to adjust for other IDE)
        // Not sure if the toolkit is thread safe...
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), "//src//" + "personTestData.txt");

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for(Person rec : recs)
            {
                writer.write(rec.toCSVRecord());
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
