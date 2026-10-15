package app;

import model.Parcel;
import util.Validation;

import java.util.Map;
import java.util.LinkedHashMap;

import java.util.Collection;
import java.util.Collections;

public class ParcelManager {
    private final Map<String, Parcel> parcels = new LinkedHashMap<String, Parcel>();

    private static final ParcelManager INSTANCE = new ParcelManager();
    private ParcelManager() {}

    public static ParcelManager getInstance() {
        return INSTANCE;
    }
    
    public void addParcel(Parcel parcel) {
        Validation.validateNullObjects(parcel);
        String trackingNumber = parcel.getTrackingNumber();

        Validation.validateTrackingNumber(trackingNumber, false);
        parcels.put(trackingNumber, parcel);
    }

    public Collection<Parcel> getAllParcels() {
        return Collections.unmodifiableCollection(parcels.values());
    }

    public Parcel getParcel(String trackingNumber) {
        return parcels.get(trackingNumber);
    }

    public boolean containsKey(String trackingNumber) {
        return parcels.containsKey(trackingNumber);
    }
}