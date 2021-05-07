package other;

/**
 * 求立方根
 * @author yuanshancheng
 * @date 2021/5/3
 */
public class Cube {

    // 误差
    private static double DIFF = 1e-1;
    private static double EXACT_DIFF = 1e-8;

    public static void main(String[] args) {
        double d = 1;
        for (int i = 0; i < 20; ++i) {
            d *= 10;
            double first = doubleSplit(d);
            double second = newton(d);
            double di = Math.abs(first - second);
            if (di < DIFF) {
                System.out.println("无误差");
            } else {
                System.out.printf("%.10f,%f,%f,%f\n", d, di, first, second);
            }
        }
    }

    /**
     * 牛顿迭代法
     *  当d为小数时，误差可能大于DIFF。当入参越小产生的误差越大，仅当入参小于误差系数才会出现不准，导致产生的误差大于DIFF
     *  当d为整数时，误差肯定小于DIFF
     * @param d
     * @return
     */
    private static double newton(double d) {
        double last = d;
        double cur = last - (pow(last, 3) - d) / (3 * last * last);
        while (Math.abs(cur - last) > DIFF) {
            last = cur;
            cur = last - (pow(last, 3) - d) / (3 * last * last);
        }
        return cur;
    }

    // 二分法，更加精确
    private static double doubleSplit(double d) {
        double left = Math.min(1, d);
        double right = Math.max(1, d);
        double mid = left;
        while (right - left > EXACT_DIFF) {
            mid = (left + right) / 2;
            double p = pow(mid, 3);
            if (p > d) {
                right = mid;
            } else if (p < d) {
                left = mid;
            } else {
                return mid;
            }
        }
        return mid;
    }

    private static double pow(double d, int n) {
        return Math.pow(d, n);
    }

}
