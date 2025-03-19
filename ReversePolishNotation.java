// In this problem, using a stack to store the integers, and as soon as we encounter any operand, we pop the top two from stack, perform
// the operation and push the evaluated result into the stack, and move forward.
class Solution {
    public int evalRPN(String[] tokens) {
        // Base case
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        // Stack for storing the integers
        Stack<Integer> st = new Stack<>();
        // Iterate over each string in tokens
        for (String s : tokens) {
            // Check if it is any of the expected operand
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                // If it is then pop the top two elements from stack
                int a = st.pop();
                int b = st.pop();
                // Perform the operation on them
                int ans = performOper(a, b, s);
                // And store back the evaluated result in the stack
                st.push(ans);
            } else {
                // Else if it is a integer, simply push it in the stack
                st.push(Integer.parseInt(s));
            }
        }
        // Our final ans lies at the top of the stack
        return st.pop();
    }

    // Method for performing the operation
    private int performOper(int a, int b, String s) {
        if (s.equals("+")) {
            return a + b;
        } else if (s.equals("-")) {
            // In case of subraction, make sure to write b-a, because b has the integer
            // which first occured in tokens
            return b - a;
        } else if (s.equals("*")) {
            return a * b;
        } else {
            // In case of division, make sure to write b/a, because b has the integer which
            // first occured in tokens
            return b / a;
        }
    }
}