package com.example;

import java.util.Scanner;
import java.util.Stack;

public class App 
{
    public static void main( String[] args )
    {
        Scanner input = new Scanner(System.in);

        String expression = input.nextLine();
        String postFix = "";
        Stack<Character> operators = new Stack<Character>();

        for(int i=0; i<expression.length(); i++){            
            if(expression.charAt(i) == ' ') continue;

            if(alphanumeric(expression.charAt(i))){
                postFix += expression.charAt(i);
                continue;
            }
            else if(expression.charAt(i) == '('){
                operators.push(expression.charAt(i));
            }
            else if(expression.charAt(i) == ')'){
                while(!operators.isEmpty() && operators.peek() != '('){
                    postFix += operators.pop();
                }

                operators.pop();
            }
            else{
                while(!operators.isEmpty() && getPrec(expression.charAt(i)) <= getPrec(operators.peek())){
                    postFix += operators.peek();
                    operators.pop();
                }
                operators.push(expression.charAt(i));
            }
        }

        while(!operators.isEmpty()){
            char op = operators.pop();

            if(op == '(') System.exit(-1); 

            postFix += op;
        }

        System.out.println(postFix);
        System.out.println(operators);
    }

    public static boolean alphanumeric(char i){
        return i >= 48 && i <= 57 ||i >= 65 && i <= 90 || i >= 97 && i <= 122;
    }

    public static int getPrec(char i){
        switch(i){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                System.out.println("Invalid operator!: " + i);
                System.exit(-1);
                return -1;
        }
    }
}
