package Algorithm;

import Structures.ListNode;

import java.util.*;
import java.util.HashSet;

public class Algorithm_1 {

    ///////////////    DAY 1  BINARY SEARCH    ////////////////
    // 704. Binary Search
    public int search(int[] arr, int target) {
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid] == target){
                return mid;
            }
            if(arr[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return -1;
    }

    // 278. First Bad Version
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;

        while(low < high){
            int mid = low + (high - low) / 2;
            boolean isBad = true ;//isBadVersion(mid)    // change with this API
            if(isBad){
                high = mid ;
            }else{
                low = mid + 1;
            }
        }
        return low;
    }

    // 35. Search Insert Position
    public int searchInsert(int[] arr, int target) {
        int low = 0;
        int high = arr.length -1;

        while(low <= high){
            int mid = low + (high - low) /2;

            if(arr[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        return low;
    }



    ///////////////    DAY 2  TWO POINTERS    ////////////////

    // 977. Squares of a Sorted Array
    public int[] sortedSquares(int[] arr) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        int[] res = new int[n];

        for(int i=n-1; i>=0; i--){

            if(Math.abs(arr[left]) < Math.abs(arr[right])){

                res[i] = arr[right] * arr[right];
                right--;

            }else{

                res[i] = arr[left] * arr[left];
                left++;

            }
        }

        return res;
    }

    // 189. Rotate Array
    public void rotate(int[] arr, int k) {
        // rotate array k times
        int n = arr.length;
        k = k % n;
        // fully reverse
        reverse(arr , 0 , n - 1);

        // reverse 0 to k-1
        reverse(arr , 0 , k - 1);

        // reverse k to n-1
        reverse(arr , k , n - 1);

    }
    private void reverse(int[] arr, int start , int end){
        while (start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }


    ///////////////    DAY 3  TWO POINTERS    ////////////////

    // 283. Move Zeroes
    public void moveZeros(int[] arr){
        // Basic idea is when the zero encountered hold that index ,
        // When next non-zero element came swap elements after that prev++
        int prev = 0;

        for(int i =0; i<arr.length; i++){
            if (arr[i] != 0) {
                swap(arr , prev , i);
                prev++;
            }
        }
    }
    public void swap(int[] arr , int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 167. Two Sum II - Input Array Is Sorted
    public int[] twoSum(int[] arr, int target){
        int left = 0;
        int right = arr.length-1;


        while(left <= right){

            if(arr[left] + arr[right] == target){
                break;
            }

            if(arr[left] + arr[right] < target){
                left++;
            }else{
                right--;
            }
        }
        return new int[] {left + 1 , right + 1};
    }


    ///////////////    DAY 4  TWO POINTERS    //////////////////////////////

    //344. Reverse String   Try inPlace
    public  void reverse(char[] s){
        int i = 0;
        int j = s.length-1;

        while(i < j){
            swap(s , i , j);
            i++;
            j--;
        }
    }
    public void swap(char[] s , int i , int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    // 557. Reverse Words in a String III
    // Input: s = "Let's take LeetCode contest"
    // Output: "s'teL ekat edoCteeL tsetnoc"
    public String reverseWords(String s){
        char[] c = s.toCharArray();

        for(int i = 0; i<c.length; i++){
            if(c[i] != ' '){
                int j = i;

                while((j+1) < c.length && c[j + 1] != ' '){
                    j++;
                }
                reverse(c , i , j);

                i = j;
            }
        }
        return new String(c);
    }
    public void reverse(char[] c , int i, int j){
        while(i < j){
            swap(c , i , j);
            i++;
            j--;
        }
    }


    ///////////////    DAY 5  TWO POINTERS    //////////////////////////////

    /// 876. Middle of the Linked List   (tortoise approach , slow fast pointer)
    public ListNode middleOfList(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){

            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /// 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n){
        // first calculate the size
        int size = 0;;
        ListNode temp = head;
        while(temp != null){
            temp = temp.next;
            size++;
        }
        // in 6 size list remove 5th node from last that is 2nd node
        if(n == size){
            return head.next;
        }
        temp = head;
        for(int i=0; i<size -n-1; i++){
            temp=temp.next;
        }
        if(n==1){
            temp.next=null;
        }else{
            temp.next=temp.next.next;
        }
        return head;
    }
    // one pass solution
    public ListNode removeNthFromEnd2(ListNode head, int n){

        ListNode start = new ListNode();
        ListNode slow = head;
        ListNode fast = head;
        start.next = head;

        //Move fast in front so that the gap between slow and fast becomes n
        for(int i=1; i<=n+1; i++)   {
            fast = fast.next;
        }
        //Move fast to the end, maintaining the gap
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Skip the desired node
        slow.next = slow.next.next;
        return start.next;

    }


    ///////////////    DAY 6  SLIDING WINDOW    ///////////////////////////


    ///////////////    DAY 7  BFS / DFS     ////////////////////////////////

    //733. Flood Fill
    public int[][] floodFill(int[][] matrix , int sr , int sc , int color){
        if(matrix[sr][sc] == color){
            return matrix;
        }
        int oldColor = matrix[sr][sc];
        fill(matrix , sr , sc , oldColor , color);
        return matrix;
    }
    public void fill(int[][] matrix , int row , int col , int oldColor , int newColor){

        int[][] dirs = {{-1 , 0}, {0 , -1}, {1 , 0}, {0 , 1}};

        matrix[row][col] = newColor;

        for(int[] i : dirs){
            int nextRow = row + i[0];
            int nextCol = col + i[1];

            if(isInside(matrix, nextRow ,nextCol) && matrix[nextRow][nextCol] == oldColor){
                fill(matrix, nextRow , nextCol , oldColor , newColor);
            }
        }
    }
    public boolean isInside(int[][] matrix , int row , int col){
        return row < matrix.length && row >= 0 && col < matrix[0].length && col >= 0;
    }


    ///////////////    DAY 8  BFS / DFS    /////////////////////////////////
    ///////////////    DAY 9  BFS / DFS    /////////////////////////////////
    ///////////////    DAY 10  RECURSION / BACKTRACKING    ////////////////
    ///////////////    DAY 11  RECURSION / BACKTRACKING    ////////////////







    public static void main(String[] args){

       int[] r1 = {1,3,4,1,2,3,1};
       int[] r2 = {3,4,4,5,2};




    }
}
