import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[]args){

        Scanner scanner = new Scanner(System.in);
        String path;


        System.out.println("Задайте путь: ");
        path = scanner.nextLine();
        Stack<String>newPath = removeUnnecessaryCommands(path);
        printNewPath(newPath);


    }

    //алгоритм удаления лишних команд из пути(метод возвращает стек, состоящий из команд, которые остались после
    // удаления излишних, из введённого пути)
    public static Stack<String> removeUnnecessaryCommands(String path){
        Stack<String> newPath = new Stack();
        String[]commands = path.split("/");
        for(int i = 0; i < commands.length; i++){
            switch(commands[i]){
                case ".":
                    if (newPath.empty()){
                        newPath.push(commands[i]);
                    }
                    break;
                case "..":
                    if (!newPath.empty() && !newPath.peek().equals("..")){
                        if (!newPath.empty() && newPath.peek().equals(".")){
                            newPath.pop();
                        }else{
                            newPath.pop();
                            if(!newPath.empty() && newPath.peek().equals(".")){
                                newPath.pop();
                            }
                            break;
                        }
                    }
                default:
                    newPath.push(commands[i]);
            }
        }
        return newPath;
    }

    //метод печатает новый путь без лиших команд(происходит итерация по стеку для добавления разделителей между командами)
    public static void printNewPath(Stack<String> newPath){
        String command;
        Iterator iterator = newPath.iterator();
        while(iterator.hasNext()){
            command = (String) iterator.next();
            if (iterator.hasNext()){
                System.out.print(command + "/");
            }else{
                System.out.print(command);
            }
        }
    }


}
