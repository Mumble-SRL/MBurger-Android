package mumble.nooko3.sdk.test.Auth;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import mumble.nooko3.sdk.Common.NKCommonMethods;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SaveTokenTest {

    @Test
    public void testSaveToken() {
        String jwt_token = "aToken123889";
        NKCommonMethods.setAccessToken(InstrumentationRegistry.getContext(), jwt_token);
        String savedToken = NKCommonMethods.getAccessToken(InstrumentationRegistry.getContext());
        assertEquals(jwt_token, savedToken);
    }
}
