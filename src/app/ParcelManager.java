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

        if (!parcel.hasDeliveryStrategy()) {
            throw new IllegalStateException("Select a delivery service before adding the parcel.");
        }

        String trackingNumber = parcel.getTrackingNumber();
        Validation.validateTrackingNumber(trackingNumber);

        if (parcels.putIfAbsent(trackingNumber, parcel) != null) {
            throw new IllegalArgumentException("The tracking number already exists.");
        }
    }

    public Collection<Parcel> getAllParcels() {
        return Collections.unmodifiableCollection(parcels.values());
    }

    public Parcel getParcel(String trackingNumber) {
        Validation.validateTrackingNumber(trackingNumber);

        Parcel parcel = parcels.get(trackingNumber);
        if (parcel == null) {
            throw new IllegalArgumentException("The tracking number does not exist.");
        }

        return parcel;
    }

    public void ensureTrackingNumberAvailable(String trackingNumber) {
        Validation.validateTrackingNumber(trackingNumber);

        if (parcels.containsKey(trackingNumber)) {
            throw new IllegalArgumentException("The tracking number already exists.");
        }
    }
}
