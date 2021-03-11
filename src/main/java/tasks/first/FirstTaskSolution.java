package tasks.first;

import java.util.ArrayDeque;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        ArrayDeque<Integer> visitedVertices = new ArrayDeque<>();
        visitedVertices.addFirst(startIndex);
        String passedVertices = "" + startIndex;
        while (!visitedVertices.isEmpty()) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[startIndex][i] && !passedVertices.contains("" + i)) {
                    visitedVertices.offerLast(i);
                    passedVertices = passedVertices + ", " + i;
                }
            }
            visitedVertices.pollFirst();
            if (visitedVertices.peekFirst() != null) {
                startIndex = visitedVertices.getFirst();
            }
        }
        return passedVertices;
    }

    @Override
    public Boolean validateBrackets(String s) {
        ArrayDeque<Character> stackOfBrackets = new ArrayDeque<>();
        char[] stringToCharArray = s.toCharArray();
        for (char c : stringToCharArray) {
            if (c == '(' || c == '[' || c == '{') {
                stackOfBrackets.push(c);
            }
            if (c == ')' && stackOfBrackets.peek() != null && stackOfBrackets.peek() == '(') {
                stackOfBrackets.pop();
            } else if (c == ')') {
                return false;
            }
            if (c == ']' && stackOfBrackets.peek() != null && stackOfBrackets.peek() == '[') {
                stackOfBrackets.pop();
            } else if (c == ']') {
                return false;
            }
            if (c == '}' && stackOfBrackets.peek() != null && stackOfBrackets.peek() == '{') {
                stackOfBrackets.pop();
            } else if (c == '}') {
                return false;
            }
        }
        return stackOfBrackets.isEmpty();
    }
    @Override
    public Long polishCalculation(String s) {
        String[] split = s.split(" ");
        ArrayDeque<Long> stackOfNumbers = new ArrayDeque<>();
        int count = 0;
        while (!split[count].equals("+") && !split[count].equals("-") && !split[count].equals("*") &&
                !split[count].equals("/")) {
            stackOfNumbers.push(Long.parseLong(split[count]));
            count++;
        }
        for (int i = count; i < split.length; i++) {
            switch (split[i]) {
                case "+": {
                    Long firstTerm = stackOfNumbers.pop();
                    Long secondTerm = stackOfNumbers.pop();
                    Long result = firstTerm + secondTerm;
                    stackOfNumbers.push(result);
                    break;
                }
                case "-": {
                    Long firstTerm = stackOfNumbers.pop();
                    Long secondTerm = stackOfNumbers.pop();
                    Long result = secondTerm - firstTerm;
                    stackOfNumbers.push(result);
                    break;
                }
                case "*": {
                    Long firstTerm = stackOfNumbers.pop();
                    Long secondTerm = stackOfNumbers.pop();
                    Long result = firstTerm * secondTerm;
                    stackOfNumbers.push(result);
                    break;
                }
                case "/": {
                    Long firstTerm = stackOfNumbers.pop();
                    Long secondTerm = stackOfNumbers.pop();
                    Long result = secondTerm / firstTerm;
                    stackOfNumbers.push(result);
                    break;
                }
            }
        }
        return stackOfNumbers.pop();
    }
}
