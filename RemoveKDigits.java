// In this problem, using the intuition that whenever the digits of the number are in increasing order, it the smallest possible number
// out of all its n! permutations. So, in our case we cannot reorder the digits, we can just remove the digits. Also, another intuition
// is that the digit on the leftmost side have the heighest weightage like in 32125, digit 3 on left have highest weightage, so removing
// 3 we will get 2125, and if we remove greater number like 5 from right we will get 3212, so always leftmost have higher weightage.

// So using above two intuitions, we will use stack to store the digits in increasing order, and if the current digit is smaller than
// the digit on top of stack, we keep on popping from stack, until there is some smaller value on top or k becomes 0 or stack is empty.
// Else we simply keep pushing to the stack. Handling other edge cases like if there are any leading zeros, or k is still > 0.

// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No
class Solution {
    public String removeKdigits(String num, int k) {
        // Base Case
        if (num == null || num.length() == 0 || num == "0" || num.length() == k) {
            return "0";
        }
        // If nothing to remove, return num
        if (k == 0) {
            return num;
        }
        // Declare a stack
        Stack<Character> s = new Stack<>();
        // Push the first digit in the stack
        s.push(num.charAt(0));
        // Iterate over the num string
        for (int i = 1; i < num.length(); i++) {
            // Get one digit at a time
            char c = num.charAt(i);
            // Now until the the stack is not empty and k is also > 0 and the our current
            // digit is smaller than the digit on the top of the stack
            while (!s.isEmpty() && k > 0 && c < s.peek()) {
                // Do stack.pop and decrement the k
                s.pop();
                k--;
            }
            // Else if the current digit value is greater just push to the stack, we will
            // get a monotonic increasing stack
            s.push(c);
        }
        // Now if k is still > 0, than do stack.pop and decrement k till it becomes zero
        while (k > 0) {
            k--;
            s.pop();
        }
        // Now we have our ans in stack
        String result = "";
        while (!s.isEmpty()) {
            // One by one pop from stack and build a string
            result = s.pop() + result;
        }
        // Handle the leading zeros scenario
        while (result.length() > 1 && result.charAt(0) == '0') {
            result = result.substring(1);
        }
        // Return result
        return result;
    }
}