import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";
        Scanner scanner = new Scanner(System.in);
        firstWordList = scanner.nextLine();
        secondWordList = scanner.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        result.stream().forEach(System.out::println);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        List<String> firstList = Arrays.asList(firstWordList.split(","));
        List<String> secondList = Arrays.asList(secondWordList.split(","));

        List<String> firstEmplyWordList = firstList.stream().filter(String::isEmpty).collect(Collectors.toList());
        List<String> secondEmplyWordList = secondList.stream().filter(String::isEmpty).collect(Collectors.toList());

        List<String> firstNotLetterWordList = firstList.stream().filter(word -> word.matches(".*[^a-zA-Z].*")).collect(Collectors.toList());
        List<String> secondNotLetterWordList = secondList.stream().filter(word -> word.matches(".*[^a-zA-Z].*")).collect(Collectors.toList());

        if (firstEmplyWordList.size()>0 || secondEmplyWordList.size()>0 || firstNotLetterWordList.size()>0 || secondNotLetterWordList.size()>0) {
            throw new RuntimeException("input not valid");
        }

        List<String> firstUppercaseList = firstList.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> commonWordList = secondList.stream().map(String::toUpperCase).filter(firstUppercaseList::contains).distinct().collect(Collectors.toList());

        List<String> result = commonWordList.stream().map(word -> word.replace("", " ").trim()).collect(Collectors.toList());
        return result;
    }
}
