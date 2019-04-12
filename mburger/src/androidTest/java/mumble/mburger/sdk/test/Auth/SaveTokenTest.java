package mumble.mburger.sdk.test.Auth;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import mumble.mburger.sdk.Common.MBCommonMethods;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SaveTokenTest {

    @Test
    public void testSaveToken() {
        String jwt_token = "aToken123889";
        MBCommonMethods.setAccessToken(InstrumentationRegistry.getContext(), jwt_token);
        String savedToken = MBCommonMethods.getAccessToken(InstrumentationRegistry.getContext());
        assertEquals(jwt_token, savedToken);
    }
}
