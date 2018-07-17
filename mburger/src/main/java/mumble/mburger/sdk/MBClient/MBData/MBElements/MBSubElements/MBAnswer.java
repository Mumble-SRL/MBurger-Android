package mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements;

import java.io.Serializable;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Single answer inside the {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBPollAnswers MBPollAnswers}
 *
 * @author  Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAnswer implements Serializable {

    /**Index of the answer*/
    private int index;

    /**Text of the answer*/
    private String answer;

    /**Number of votes of the answer*/
    private int result;

    public MBAnswer(int index, String answer, int result) {
        this.index = index;
        this.answer = answer;
        this.result = result;
    }

    /**Get the index of the answer*/
    public int getIndex() {
        return index;
    }

    /**Sets the index of the answer*/
    public void setIndex(int index) {
        this.index = index;
    }

    /**Get the text of the answer*/
    public String getAnswer() {
        return answer;
    }

    /**Sets the text of the answer*/
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**Get the result of the answer*/
    public int getResult() {
        return result;
    }

    /**Sets the result of the answer*/
    public void setResult(int result) {
        this.result = result;
    }
}
