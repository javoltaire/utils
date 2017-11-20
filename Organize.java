import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Organize{
    /**
     * Recursive argument if there is one
     */
    private static final String RECURSIVE_OPTION = "-r";

    /**
     * Entry point of the program
     */
    public static void main(String[] args) {
        // Make sure we have at least two arguments
        if(args.length < 2){
            printUsage();
        }
        else{
            String src = args[0];
            String baseDestFolder = args[1];
            boolean isRecursive = false;
            if(args.length > 2)
                isRecursive = args[2].equals(RECURSIVE_OPTION);

            organize(src, baseDestFolder, isRecursive);
        }
    }

    /**
     * Prints out instructions on how to use the program
     */
    public static void printUsage() {
        System.out.println("Usage:");
        System.out.println("$Organize /path/to/file/or/folderOfFiles/to/be/moved /root/folder/to/move/files/to");
    }




    public static void organize(final String src, final String baseDestFolder, boolean isRecursive) {
        Path source = FileSystems.getDefault().getPath(src);
        LocalDateTime dateTime = LocalDateTime.now();
        Path destinationPath = createPathFromDate(destBase, localDate);
        move(source, destinationPath);
    }

    public static void move(Path sourceFile, Path destFolder) {
        System.out.println("Moving " +  sourceFile + " to " + destFolder);
    }

    /**
     * 
     */
    public static Path createPathFromDate(String base, LocalDate localDate) throws Exception{
        Path destPath = FileSystems.getDefault().getPath(base + "\\" + localDate.getYear());
        destPath = checkOrCreateFolderPath(destPath);
        destPath = checkOrCreateFolderPath(destPath.resolve(String.valueOf(localDate.getMonthValue())));
        destPath = checkOrCreateFolderPath(destPath.resolve(String.valueOf(localDate.getDayOfMonth())));
        return  destPath;
    }

    /**
     * Checks the given path. If it doesn't exist, create it
     * param path The path to check
     */
    public static Path checkOrCreateFolderPath(Path path) throws IOException{
        if(!Files.exists(path)){
            path = Files.createDirectory(path);
        }
        return path;
    }

    /**
     * Converts a file name to a LocalDate
     * @param fileName
     * @param format
     * @return
     * @throws ParseException
     */
    public static LocalDate getDateFromFileName(String fileName, String format) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(fileName);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Removes the extension from a file name
     * @param fileName The name of the file with the extension
     * @return The name of the file without the extension
     */
    public static String removeExtension(String fileName){
        String name = fileName;
        if (name.indexOf(".") > 0)
            name = name.substring(0, name.lastIndexOf("."));
        return name;
    }

}
