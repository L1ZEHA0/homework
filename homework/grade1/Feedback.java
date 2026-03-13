package homework.grade1;

public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2, String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        } else {
            completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5).toString();
        }
        
        longFeedback = checkFeedbackLength(completeFeedback);
        createReviewID(firstName, lastName, completeFeedback);
    }

    private String feedbackUsingConcatenation(String sent1, String sent2, String sent3, String sent4, String sent5) {
        String concatenatedFeedback = sent1 + sent2 + sent3 + sent4 + sent5;
        return concatenatedFeedback;
    }

    private StringBuilder feedbackUsingStringBuilder(String sent1, String sent2, String sent3, String sent4, String sent5) {
        StringBuilder sb = new StringBuilder();
        sb.append(sent1);
        sb.append(sent2);
        sb.append(sent3);
        sb.append(sent4);
        sb.append(sent5);
        return sb;
    }

    private boolean checkFeedbackLength(String completeFeedback) {
        if (completeFeedback.length() > 500) {
            longFeedback = true;
        } else {
            longFeedback = false;
        }
        return longFeedback;
    }

    private void createReviewID(String firstName, String lastName, String completeFeedback) {
        String namePart = (firstName + lastName).substring(2, 6).toUpperCase();
        String feedbackPart = completeFeedback.substring(10, 15).toLowerCase();
        int feedbackLength = completeFeedback.length();
        long timestamp = System.currentTimeMillis();
        
        reviewID = namePart + feedbackPart + feedbackLength + "_" + timestamp;
        reviewID = reviewID.replace(" ", "");
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", completeFeedback='" + completeFeedback + '\'' +
                ", longFeedback=" + longFeedback +
                ", reviewID='" + reviewID + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String sentence1 = "我对这项服务非常满意。";
        String sentence2 = "这款电动自行车骑起来非常舒适。";
        String sentence3 = "这款电动自行车的电池续航能力令人惊艳。";
        String sentence4 = "客户服务贴心且响应及时。";
        String sentence5 = "我会把这款电动自行车推荐给亲朋好友。";

        Feedback feedback = new Feedback("John", "Doe", "john.doe@example.com");
        feedback.analyseFeedback(false, sentence1, sentence2, sentence3, sentence4, sentence5);
        System.out.println(feedback);
    }
}
