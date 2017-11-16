package io.github.tkaczenko.Authentication.src.security.authentication;

/**
 * Created by tkaczenko on 11.10.16.
 */
public class PassAuthDataProvider implements AuthDataProvider {
    @Override
    public String getAuthKey(String login) {
        String res = null;
        switch (login) {
            case "волков":
                res = "дєкиалмфєп";
                break;
            case "корсун":
                res = "гіаукїфп";
                break;
            case "пащенко":
                res = "спгдсщ";
                break;
            case "коваленко":
                res = "авко";
                break;
            case "пєтухова":
                res = "авілгм";
                break;
            case "войтюк":
                res = "їтлфо.";
                break;
            case "щербаков":
                res = "фщбщцжй'";
                break;
            case "шишкун":
                res = "хепбтфдїй'";
                break;
            case "демченко":
                res = "йвткяфбї";
                break;
            case "лисенко":
                res = "обкртбхлїю";
                break;
            case "смірнова":
                res = "іпслчю";
                break;
            case "таран":
                res = "уфрічю";
                break;
            case "волошин":
                res = "йланвс";
                break;
            case "киришко":
                res = "икшкїд";
                break;
            case "станько":
                res = "тапєїь";
                break;
        }
        return res;
    }
}
