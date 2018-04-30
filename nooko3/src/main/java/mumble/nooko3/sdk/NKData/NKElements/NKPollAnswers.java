package mumble.nooko3.sdk.NKData.NKElements;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;
import mumble.nooko3.sdk.NKData.NKElements.NKSubElements.NKAnswer;

/**
 * Identifies an array of answers for a poll.
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKPollAnswers extends NKClass {

    /**
     * Array of {@link NKAnswer NKAnswer}
     */
    private ArrayList<NKAnswer> answers;

    /**
     * Time the poll ends
     */
    private long ends_at;

    /**
     * If you have voted, index of the answer
     */
    private int index_answer = -1;

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    public NKPollAnswers(long id, String name, ArrayList<NKAnswer> answers, long ends_at, int index_answer) {
        this.answers = answers;
        this.ends_at = ends_at;
        this.index_answer = index_answer;
        initialize(id, name, NKConstants.type_poll);
    }

    /**
     * Get the array of {@link NKAnswer NKAnswer}
     */
    public ArrayList<NKAnswer> getAnswers() {
        return answers;
    }

    /**
     * Sets the array of {@link NKAnswer NKAnswer}
     */
    public void setAnswers(ArrayList<NKAnswer> answers) {
        this.answers = answers;
    }

    /**
     * Get when the poll ends (seconds)
     */
    public long getEnds_at() {
        return ends_at;
    }

    /**
     * Get when the poll ends (Milliseconds)
     */
    public long getEnds_atMillis() {
        return TimeUnit.SECONDS.toMillis(ends_at);
    }

    /**
     * Sets when the poll ends
     */
    public void setEnds_at(long ends_at) {
        this.ends_at = ends_at;
    }

    /**
     * Gets the index of the personal answer
     */
    public int getIndex_answer() {
        return index_answer;
    }

    /**
     * Sets when the index of the personal answer
     */
    public void setIndex_answer(int index_answer) {
        this.index_answer = index_answer;
    }
}
