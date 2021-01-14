## LeetCode-part1

### Day2:整数反转

**整数反转**

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

注意：

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

**题解**

每次除10取余数，然后将该数除10，本次所得的余数*10和下次的余数相加，即可将该数反转，但是要考虑溢出的问题。

溢出条件有两个，一个是大于整数最大值MAX_VALUE，另一个是小于整数最小值MIN_VALUE，设当前计算结果为ans，下一位为pop。

+ 从ans * 10 + pop > MAX_VALUE这个溢出条件来看

  - 当出现 ans > MAX_VALUE / 10 且 还有pop需要添加 时，则一定溢出
    当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7是2^31 - 1的个位数
  - 从ans * 10 + pop < MIN_VALUE这个溢出条件来看
    当出现 ans < MIN_VALUE / 10 且 还有pop需要添加 时，则一定溢出
    当出现 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出，8是-2^31的个位数

  代码如下：

  ``` java
  class Solution {
      public int reverse(int x) {
          int ans = 0;
          while(x != 0){
              int pop = x % 10;
              x/=10;
              if(ans > Integer.MAX_VALUE/10 ||(ans==Integer.MAX_VALUE/10 && pop >7))return 0;
              if(ans < Integer.MIN_VALUE/10 ||(ans==Integer.MIN_VALUE/10 && pop <-8))return 0;
              ans = ans *10 + pop;
              
          }
  
          return ans;
      }
  }
  ```



### Day4:罗马数字转整数

**罗马数字转整数**

罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

**题解**

将字符串转换为字符数组，遍历该数组，用switch语句对不同字符所代表的数字进行相加最终得到结果。同时需要注意以上六种特殊情况，出现以上情况时，将该字符所对应的数减去即可。

```java
class Solution {
    public int romanToInt(String s) {
        int result = 0;
        char ss[]=s.toCharArray();
        for(int i=0;i<ss.length;i++){
            switch(ss[i]){
                case'M':
                    result += 1000;
                    break;
                case'D':
                    result += 500;
                    break;
                case'C':
                    if(i+1<ss.length&&(ss[i+1]=='D'||ss[i+1]=='M')){
                        result -= 100;
                    }else{
                        result +=100;
                    }
                    break;
                case'L':
                    result += 50; 
                    break;
                case'X':
                    if(i+1<ss.length&&(ss[i+1]=='L'||ss[i+1]=='C')){
                        result -=10;
                    }else{
                        result +=10;
                    }       
                    break;
                case'V':
                    result += 5;    
                    break;
                case'I':
                    if(i+1<ss.length&&(ss[i+1]=='V'||ss[i+1]=='X')){
                        result -=1;
                    }else{
                        result +=1;
                    }
            }
        }
        return result;
    }
}
```

### Day5:最长公共前缀

**最长公共前缀**

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 `""`。

**示例 1：**

```
输入：strs = ["flower","flow","flight"]
输出："fl"
```

当字符串数组长度为 0 时则公共前缀为空，直接返回
令最长公共前缀 ans 的值为第一个字符串，进行初始化
遍历后面的字符串，依次将其与 ans 进行比较，两两找出公共前缀，最终结果即为最长公共前缀
如果查找过程中出现了 ans 为空的情况，则公共前缀不存在直接返回

代码如下：

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        
   if(strs == null||strs.length==0) 
            return "";
        String prefix = strs[0];
        for(int i =1;i<strs.length;i++) {
            int j=0;
            for(;j<prefix.length() && j < strs[i].length();j++) {
                if(prefix.charAt(j) != strs[i].charAt(j))
                    break;
            }
            prefix = prefix.substring(0, j);
            if(prefix.equals(""))
                return prefix;
        }
        return prefix;
    }

}
```

### Day6:有效括号

**有效括号**

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true

**题解**

判断括号的有效性可以使用「栈」这一数据结构来解决。依次遍历字符串中的每个字符，如果是左括号就将其入栈，考虑到如果第一个为右括号则直接返回false，当出现右括号时，栈顶元素出栈，对比右括号与栈顶括号是否匹配。

代码如下：

```java
class Solution {
    public boolean isValid(String s) {
        //if(s.length==0||s==null)return true;

        Stack<Character> stack = new Stack<>();

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            if(c=='('||c=='['||c=='{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }

                char top = stack.pop();

                if(c==')'&&top!='(')
                    return false;
                if(c==']'&&top!='[')
                    return false;
                if(c=='}'&&top!='{')
                    return false;


            }

        } 
        return stack.isEmpty();

    }
}
```

### Day8:删除排序数组中的重复项

**删除排序数组中的重复项**

给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

示例 1:

给定数组 nums = [1,1,2], 

函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 

你不需要考虑数组中超出新长度后面的元素。

**题解**

定义一个 j 来统计数组中元素重复出现的个数，每次将下一个元素复制到未重复元素的后面，即nums[i+1-j]=nums[i+1];最后返回数组长度减重复个数 j 即可。

代码如下：

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int j = 0;
        for(int i=0;i<len-1;i++){
            if(nums[i]==nums[i+1]){
                j++;
            //    nums [i+1-j]=nums[i+1];
            }
            nums [i+1-j]=nums[i+1];
            //len -= j;
        }
       len -= j;
        return len;
    }
}
```

### Day12:外观数列

**外观数列**

给定一个正整数 n ，输出外观数列的第 n 项。

「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。

你可以将其视作是由递归公式定义的数字字符串序列：

countAndSay(1) = "1"
countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
前五项如下：

1.     1
2.     11
3.     21
4.     1211
5.     111221
第一项是数字 1 
描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"

**题解**

需要描述前一项，因此采用递归的方式，当n=1时，返回"1"。n>1时，返回countAndsay(n-1)。

定义一个计数元素、可变长字符串，定义当前的字符并将其初始化为第一个字符。遍历每一个字符，统计相邻数字的重复个数，最后将重复个数与该数字添加进字符串中。

代码如下：

```java
class Solution {
    public String countAndSay(int n) {
        if(n==1) return "1";
        return say(countAndSay(n-1));
    }

    public String say(String s){
        int count = 1;//Number of occurrences
        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);//The current digital

        for(int i = 1;i<s.length();i++){
            if(c == s.charAt(i)){
                count++;

            }else{
                sb.append(count);
                sb.append(c);
                count=1;
                c = s.charAt(i);
            }
        }
        //If the last two digits are the same, only the count operation is performed and not added to sb.
        sb.append(count);
        sb.append(c);
        return sb.toString();
    }
}
```

### Day13:最大子序和

**最大子序和**

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

**题解**

首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans
如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ans = nums[0];
        for(int i = 0;i<nums.length;i++){
            if(sum>0){
                sum += nums[i];
            }else{
                sum = nums[i];
            }
            ans = Math.max(sum,ans);
        }
        return ans;
    }
}
```

### Day15:加一

**加一**

给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1：

输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。

**题解**

末位无进位，则末位加一即可，因为末位无进位，前面也不可能产生进位，比如 45 => 46
末位有进位，在中间位置进位停止，则需要找到进位的典型标志，即为当前位 %10 后为 0，则前一位加 1，直到不为 0 为止，比如 499 => 500
末位有进位，并且一直进位到最前方导致结果多出一位，对于这种情况，需要在第 2 种情况遍历结束的基础上，进行单独处理，比如 999 => 1000

```java
class Solution {
    public int[] plusOne(int[] digits) {
        for(int i=digits.length-1;i>=0;i--){
            digits[i]++;
            digits[i] %= 10;
            if(digits[i]!=0) return digits;

        }
        digits = new int[digits.length+1];
        digits[0]=1;
        return digits;


    }
}
```

### Day16:二进制求和

**二进制求和**

给你两个二进制字符串，返回它们的和（用二进制表示）。

输入为 非空 字符串且只包含数字 1 和 0。

示例 1:

输入: a = "11", b = "1"
输出: "100"

**题解**

首先定义变长字符串ans和进位carry，从后向前依次遍历字符串a和b，每次循环需要将进位和a、b的当前数字相加，因此定义sum并将其初始化为每次的进位carry，a和b长度不一定相等所以需将短字符串进行补0，利用char类型的ASCII值进行运算，如果最后一次运算有进位则将其添加到ans中，因为是从后向前相加然后存入ans中 因此结果需要反转。

代码如下：

```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        for(int i=a.length()-1, j=b.length()-1;i>=0||j>=0;i--,j--){
            int sum = carry;
            sum += i>=0 ? a.charAt(i) - '0' : 0;
            sum += j>=0 ? b.charAt(j) - '0' : 0;
            ans.append(sum%2);
            carry = sum/2;
        }
        ans.append(carry>0 ? carry : "");
        return ans.reverse().toString();
    }
}
```

### Day17:x的平方根

**x的平方根**

实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

**示例 2:**

```
输入: 8
输出: 2
说明: 8 的平方根是 2.82842..., 
     由于返回类型是整数，小数部分将被舍去。
```

**题解**

由于 x平方根的整数部分ans 是满足 k^2  ≤x 的最大k值，因此我们可以对 k进行二分查找，从而得到答案。

二分查找的下界为 0，上界可以粗略地设定为 x。在二分查找的每一步中，我们只需要比较中间元素mid 的平方与 x 的大小关系，并通过比较的结果调整上下界的范围。由于我们所有的运算都是整数运算，不会存在误差，因此在得到最终的答案ans 后，也就不需要再去尝试 ans+1 了。

```java
class Solution {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int ans =0;
        while(left<=right){
            int mid = left + (right - left)/2;
            if((long)mid*mid <= x){
                ans = mid;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return ans;
    }
}
```

### Day18:爬楼梯

**爬楼梯**

假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

**示例 1：**

```
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
```

**题解**

本问题其实常规解法可以分成多个子问题，爬第n阶楼梯的方法数量，等于 2 部分之和

- 爬上 n-1n−1 阶楼梯的方法数量。因为再爬1阶就能到第n阶
- 爬上 n-2n−2 阶楼梯的方法数量，因为再爬2阶就能到第n阶

```java
class Solution {
    public int climbStairs(int n) {
        int x1= 1;
        int x2=2;
        int result =0;
        if(n==1)return x1;
        if(n==2)return x2;
        for(int i =3;i<=n;i++){
            result =x1+x2;
            x1=x2;
            x2=result;
        }
        return result;
    }
}
```

### Day20:合并两个有序数组

**合并两个有序数组**

给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 有足够的空间（空间大小等于 m + n）来保存 nums2 中的元素。

**示例 1：**

```
输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
输出：[1,2,2,3,5,6]
```

**题解**

因为 nums1 的空间都集中在后面，所以从后向前处理排序的数据会更好，节省空间，一边遍历一边将值填充进去
设置指针 len1 和 len2 分别指向 nums1 和 nums2 的有数字尾部，从尾部值开始比较遍历，同时设置指针 len 指向 nums1 的最末尾，每次遍历比较值大小之后，则进行填充
当 len1<0 时遍历结束，此时 nums2 中海油数据未拷贝完全，将其直接拷贝到 nums1 的前面，最后得到结果数组

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len=nums1.length-1;
        int len1=m-1;
        int len2=n-1;
        while(len1>=0 && len2 >=0){
            if(nums1[len1]>nums2[len2]){
                nums1[len--]=nums1[len1--];
            }else{
                nums1[len--]=nums2[len2--];
            }
        }
        while(len1<0 && len2 >=0){
            nums1[len--]=nums2[len2--];
        }
        //return nums1[];
    }
}
```

### Day21:相同的树

**相同的树**

给定两个二叉树，编写一个函数来检验它们是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

示例 1:

        输入:   1         1
              / \       / \
             2   3     2   3
        [1,2,3],   [1,2,3]
        输出: true

**题解**

终止条件与返回值：

当两棵树的当前节点都为 null 时返回 true

当其中一个为 null 另一个不为 null 时返回 false

当两个都不为空但是值不相等时，返回 false

代码如下：

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }else if(p==null || q==null){
            return false;
        }else if(p.val != q.val){
            return false;
        }else{
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        }
    }
}
```



