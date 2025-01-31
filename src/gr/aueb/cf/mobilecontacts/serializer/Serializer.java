package gr.aueb.cf.mobilecontacts.serializer;

import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;

public class Serializer {

    /**
     * No instance of this class should be available
     */
    private Serializer() {

    }


    public static String serializeDTO(MobileContactReadOnlyDTO readOnlyDTO){
        return "ID: " + readOnlyDTO.getId() + ", Όνομα: " + readOnlyDTO.getFirstname()
                + ", Επώνυμο: " + readOnlyDTO.getLastname() + ", Τηλεφωνικός Αριθμός: " + readOnlyDTO.getPhoneNumber();
    }
}
