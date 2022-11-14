package com.example.javastudy.juc;

/**
 * 给定两个从大到小排好序的数组A和B，给定K，找到A和B中第K大元素。
 *
 * func FindTopK(A, B []int, K int) (int, error)
 *
 * A=[10, 7, 3], B=[9, 6]
 *
 * K=2, return 9
 *
 * K=1, return 10
 */
public class Solution {

    public static void main(String[] args) {
        int[] a = {10,7,3};
        int[] b = {9,6};
        Solution solution = new Solution();
        int k = 4;
        int kth = solution.getKth(a, 0, a.length, b, 0, b.length, k);
        System.out.println(kth);
    }


    public int getKth(int[] a, int aLeft, int aRight, int[] b, int bLeft, int bRight,int k) {
        //数组长度
        int aLen = aRight - aLeft + 1;
        int bLen = bRight - bLeft + 1;
        //递归边界条件
        if (a == null || b == null || aLen < 0 || bLen < 0 || k <= 0 || k > aLen + bLen) {
            return -1;
        }
        //a b 长度为0
        if (aLen == 0) {
            return b[bLeft + k - 1];
        }
        if (bLen == 0) {
            return a[aLeft + k - 1];
        }
        //a b 长度不为0，但k==1
        if (k == 1) {
            return Math.min(a[aLeft], b[bLeft]);
        }


        int aMid, bMid;//长度，不是中间值的下标
        if ((aLen & 0x1) == 1) { //两数组长度为奇数
            aMid = aLen / 2;
            bMid = bLen / 2;
        } else {  //两数组长度为偶数
            aMid = aLen / 2 - 1;
            bMid = bLen / 2;
        }
        //中点坐标
        int aMidIndex = aLeft + aMid;//aMidIndex-aLeft+1=aMid+1
        int bMidIndex = bLeft + bMid;

        //两个数组长度为奇数时，a数组长度2*x+1，b数组长度为2*y+1，总数组长度和2*x+2*y+2，比较k与x+y+1关系
        //两个数组长度为偶数时，a数组长度2*x+2，b数组长度为2*y，总数组长度为2*x+2*y+2，比较k与x+y+1关系
        if (a[aMidIndex] <= b[bMidIndex]) {
            if (k <= (aMid + bMid + 1)) {//舍弃b的后半段
                return getKth(a, aLeft, aRight, b, bLeft, bMidIndex - 1, k);
            } else {//舍弃a的前半段
                return getKth(a, aMidIndex + 1, aRight, b, bLeft, bRight, k - aMid - 1);
            }
        } else {
            if (k <= (aMid + bMid + 1)) {//舍弃a的后半段
                return getKth(a, aLeft, aMidIndex - 1, b, bLeft, bRight, k);
            } else {//舍弃b的前半段
                return getKth(a, aLeft, aRight, b, bMidIndex + 1, bRight, k - bMid - 1);
            }
        }
    }

}
