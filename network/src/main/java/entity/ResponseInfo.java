package entity;

/**
 *
 *
 *
 * @author: ywzheng
 * @Description: TODO
 * @date: 2021/9/17 9:14 AM
 */
public class ResponseInfo {

    private double score;
    private double time;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "score=" + score +
                ", time=" + time +
                '}';
    }
}
