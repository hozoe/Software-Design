package assignment5;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractWatchable implements Watchable{
    String aTitle;
    Language aLanguage;
    String aStudio;
    Map<String, String> aTags = new HashMap<>();

    AbstractWatchable(String pTitle, Language pLanguage, String pStudio){
        assert pTitle != null && pLanguage != null && pStudio != null;
        this.aTitle = pTitle;
        this.aLanguage = pLanguage;
        this.aStudio = pStudio;
    }

    public String getTitle(){
        return this.aTitle;
    }
    public Language getLanguage(){
        return this.aLanguage;
    }
    public String getStudio(){
        return this.aStudio;
    }

    public String setInfo(String pKey, String pValue) {
        assert pKey != null && !pKey.isBlank();
        if (pValue == null) {
            return aTags.remove(pKey);
        }
        else {
            return aTags.put(pKey, pValue);
        }
    }
    public boolean hasInfo(String pKey) {
        assert pKey != null && !pKey.isBlank();
        return aTags.containsKey(pKey);
    }

    public String getInfo(String pKey) {
        assert hasInfo(pKey);
        return aTags.get(pKey);
    }


}
