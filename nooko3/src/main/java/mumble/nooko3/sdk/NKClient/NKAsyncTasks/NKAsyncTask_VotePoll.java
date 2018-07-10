package mumble.nooko3.sdk.NKClient.NKAsyncTasks;

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

import mumble.nooko3.sdk.Common.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.Common.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiPayloadKeys;
import mumble.nooko3.sdk.NKClient.NKApiResultsLIsteners.NKApiVotePollListener;
import mumble.nooko3.sdk.Common.NKCommonMethods;
import mumble.nooko3.sdk.NKData.NKElements.NKSubElements.NKAnswer;

public class NKAsyncTask_VotePoll extends AsyncTask<Void, Void, Void> {

    private WeakReference<Context> weakContext;
    private String action = NKAPIConstants.ACTION_VOTE_POLL;
    private NKApiVotePollListener listener;

    private long element_id;
    private int vote;
    private ArrayList<NKAnswer> answers;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private int mine;

    public NKAsyncTask_VotePoll(Context context, long element_id, int vote, ArrayList<NKAnswer> answers) {
        this.weakContext = new WeakReference<>(context);
        this.element_id = element_id;
        this.vote = vote;
        this.answers = answers;
    }

    public NKAsyncTask_VotePoll(Context context, long element_id, int vote, ArrayList<NKAnswer> answers,
                                NKApiVotePollListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.element_id = element_id;
        this.vote = vote;
        this.answers = answers;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (NKApiManagerUtils.hasMapOkResults(map, false)) {
            getPayload((String) map.get(NKApiManagerConfig.AM_PAYLOAD));
            result = NKApiManagerConfig.RESULT_OK;
        } else {
            if (map.containsKey(NKApiManagerConfig.AM_RESULT)) {
                result = (int) map.get(NKApiManagerConfig.AM_RESULT);
            } else {
                result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
            }

            if (map.containsKey(NKApiManagerConfig.AM_ERROR)) {
                error = (String) map.get(NKApiManagerConfig.AM_ERROR);
            } else {
                error = NKCommonMethods.getErrorMessageFromResult(weakContext.get(), result);
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
                i.putExtra(NKApiPayloadKeys.key_my_vote, mine);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
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
        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_VOTE_POLL, values,
                NKApiManagerConfig.MODE_POST, true, false);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            mine = jBody.getInt("mine");
            JSONArray jResults = jBody.getJSONArray("results");
            for(int i = 0; i < answers.size(); i++){
                answers.get(i).setResult(jResults.getInt(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
