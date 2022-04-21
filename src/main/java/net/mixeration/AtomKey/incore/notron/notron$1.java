package net.mixeration.AtomKey.incore.notron;

import com.google.gson.JsonObject;
import net.mixeration.AtomKey.incore.cloud;
import net.mixeration.AtomKey.outcore.ConnectionBuilder;
import net.mixeration.AtomKey.outcore.CustomConnection;
import net.mixeration.AtomKey.outcore.IUpdateChecker;

import java.io.IOException;

public abstract class notron$1 implements IUpdateChecker {

    public static void checkUpdates(int pluginID){
        String ID = "https://songoda.com/api/v2/products/" + pluginID;
        try{
            CustomConnection connection = ConnectionBuilder.connect(ID);
            if(connection.isValidResponse() && connection.isResponseNotNull()){
                JsonObject json = connection.getResponseJson();
                if(json != null && !json.isJsonNull()){
                    String version = json.get("data").getAsJsonObject().get("versions").getAsJsonArray().get(0).getAsJsonObject().get("version").getAsString();
                    cloud.message("&dAtomKey &7New version: &a" + version);
                }else{
                    cloud.message("&dAtomKey &7New version can`t found.");
                }
            }else{
                cloud.message("&dAtomKey &7Something went wrong.");
            }
        }catch (IOException ex){
            ex.printStackTrace();
            cloud.message("&dAtomKey &7Something went wrong.");
        }
    }
}