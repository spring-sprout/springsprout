package springsprout.modules.study.support;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 20
 * Time: 오전 11:04:20
 */
public class StudyURLRedirectionUtils {

    public static String redirectPresentationView(int studyId, int meetingId, int presentationId) {
        return "redirect:/study/" + studyId + "/meeting/" + meetingId + "/presentation/" + presentationId + "";
    }

    public static String redirectMeetingView(int studyId, int meetingId) {
        return "redirect:/study/" + studyId + "/meeting/" + meetingId + "";
    }

    public static String redirectStudyView(int studyId) {
        return "redirect:/study/" + studyId + "";
    }

}
