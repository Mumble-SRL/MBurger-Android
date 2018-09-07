package mumble.mburger.sdk.MBClient.MBAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiPayloadKeys;
import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiVotePollListener;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBAnswer;

public class MBAsyncTask_VotePoll extends AsyncTask<Void, Void, Void> {

    private WeakReference<Context> weakContext;
    private String action = MBAPIConstants.ACTION_VOTE_POLL;
    private MBApiVotePollListener listener;

    private long element_id;
    private int vote;
    private ArrayList<MBAnswer> answers;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private int mine;

    public MBAsyncTask_VotePoll(Context context, long element_id, int vote, ArrayList<MBAnswer> answers) {
        this.weakContext = new WeakReference<>(context);
        this.element_id = element_id;
        this.vote = vote;
        this.answers = answers;
    }

    public MBAsyncTask_VotePoll(Context context, long element_id, int vote, ArrayList<MBAnswer> answers,
                                MBApiVotePollListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.element_id = element_id;
        this.vote = vote;
        this.answers = answers;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (MBApiManagerUtils.hasMapOkResults(map, false)) {
            getPayload((String) map.get(MBApiManagerConfig.AM_PAYLOAD));
            result = MBApiManagerConfig.RESULT_OK;
        } else {
            if (map.containsKey(MBApiManagerConfig.AM_RESULT)) {
                result = (int) map.get(MBApiManagerConfig.AM_RESULT);
            } else {
                result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
            }

            if (map.containsKey(MBApiManagerConfig.AM_ERROR)) {
                error = (String) map.get(MBApiManagerConfig.AM_ERROR);
            } else {
                error = MBCommonMethods.getErrorMessageFromResult(weakContext.get(), result);
            }
        }
        return null;
    }

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(MBApiPayloadKeys.key_my_vote, mine);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onPollVotedApiError(error);
                } else {
                    listener.onPollVotedApiResult(mine);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("element_id", Long.toString(element_id));
        values.put("vote", Integer.toString(vote));
        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_VOTE_POLL, values,
                MBApiManagerConfig.MODE_POST, true, false);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            mine = jBody.getInt("mine");
            JSONArray jResults = jBody.getJSONArray("results");
            for (int i = 0; i < answers.size(); i++) {
                answers.get(i).setResult(jResults.getInt(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
