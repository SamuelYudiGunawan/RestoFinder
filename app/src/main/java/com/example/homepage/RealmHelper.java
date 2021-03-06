package com.example.homepage;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void save(final RestoModel restaurantModels) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    RestoModel model = realm.copyToRealm(restaurantModels);
                } else {
                    Log.e("Failed", "execute: Database not Exist");
                }
            }
        });
    }

    public List<RestoModel> getAllRestaurant() {
        RealmResults<RestoModel> results = realm.where(RestoModel.class).findAll();
        return results;
    }


    public RealmResults<RestoModel> getRestoById(String id) {
        final RealmResults<RestoModel> results = realm.where(RestoModel.class).equalTo("id", id).findAll();

        return results;
    }

    public void delete(String id){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final RealmResults<RestoModel> model = realm.where(RestoModel.class).equalTo("id", id).findAll();
                Log.d("Model dari Delete", String.valueOf(model));
                model.deleteFirstFromRealm();
            }
        });
    }
}

