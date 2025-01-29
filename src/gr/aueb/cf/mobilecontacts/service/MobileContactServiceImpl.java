package gr.aueb.cf.mobilecontacts.service;

import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;
import gr.aueb.cf.mobilecontacts.exceptions.ContactNotFoundException;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.List;

public class MobileContactServiceImpl implements IMobileContactService{

    public MobileContactServiceImpl(IMobileContactDAO dao) {
        this.dao = dao;
    }

    private final IMobileContactDAO dao;

    @Override
    public MobileContact insertMobileContact(MobileContactInsertDTO dto) throws PhoneNumberAlreadyExistsException {
        MobileContact mobileContact;

        try {
            if (dao.phoneNumberExists(dto.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExistsException("Contact with phone number " + dto.getPhoneNumber() + " already exists.");
            }

            mobileContact = mapInsertDTOToContact(dto);

            System.err.printf("MobileContactServiceImpl Logger: %s was inserted\n", mobileContact);
            return dao.insert(mobileContact);
        } catch (PhoneNumberAlreadyExistsException e) {
            System.err.printf("MobileContactServiceImpl Logger: Contact with phone number %s already exists\n", dto.getPhoneNumber());
            throw e;
        }


    }

    @Override
    public MobileContact updateMobileContact(MobileContactUpdateDTO dto)
            throws PhoneNumberAlreadyExistsException, ContactNotFoundException {
        MobileContact mobileContact;
        MobileContact newContact;

        try {
            if (!dao.userIdExists(dto.getId())) {
                throw new ContactNotFoundException("Contact with ID: " + dto.getId() + " not found");
            }

            mobileContact = dao.getById(dto.getId());
            boolean isPhoneNumberOurOwn = mobileContact.getPhoneNumber().equals(dto.getPhoneNumber());
            boolean isPhoneNumberExists = dao.phoneNumberExists(dto.getPhoneNumber());

            if (isPhoneNumberExists && isPhoneNumberOurOwn) {
                throw new PhoneNumberAlreadyExistsException("Contact with phone number: " +  dto.getPhoneNumber()
                        + " already exists and can not be updated.");
            }

            newContact = mapUpdateDTOToContact(dto);
            System.err.printf("MobileContactServiceImpl Logger: %s was updated with the new info: %s\n", mobileContact, newContact);
            return dao.update(dto.getId(), newContact);


        } catch (PhoneNumberAlreadyExistsException | ContactNotFoundException e) {
            System.err.printf("MobileContactServiceImpl Logger: %s\n", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteContactById(Long id) throws ContactNotFoundException {

        try {
            if (!dao.userIdExists(id)) {
                throw new ContactNotFoundException("Contact with ID: " + id + " not found for delete.");
            }

            System.err.printf("MobileContactServiceImpl Logger: contact with id: %d was deleted.\n", id);
            dao.deleteById(id);
        } catch (ContactNotFoundException e) {
            System.err.printf("MobileContactServiceImpl Logger: %s\n", e.getMessage());
            throw e;
        }
    }

    @Override
    public MobileContact getContactById(Long id) throws ContactNotFoundException {
        MobileContact mobileContact;

        try {
            mobileContact = dao.getById(id);
            if (mobileContact == null) {
                throw new ContactNotFoundException("Contact with ID: " + id + " not found.");
            }

            return mobileContact;
        } catch (ContactNotFoundException e) {
            System.err.printf("Contact with ID: %d was not found to get returned\n", id);
            throw e;
        }
    }

    @Override
    public List<MobileContact> getAllContacts() {
        return dao.getAll();
    }

    @Override
    public MobileContact getContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        MobileContact mobileContact;

        try {
            mobileContact = dao.getByPhoneNumber(phoneNumber);
            if (mobileContact == null) {
                throw new ContactNotFoundException("Contact with phone number: " + phoneNumber + " not found.");

            }
            return mobileContact;
        } catch (ContactNotFoundException e) {
            System.err.printf("Contact with phone number: %s was not found to get returned \n", phoneNumber);
            throw e;
        }
    }

    @Override
    public void deleteContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        try {
            if (!dao.phoneNumberExists(phoneNumber)) {
                throw new ContactNotFoundException("Contact with phone number: " + phoneNumber + " not found for delete.");
            }

            System.err.printf("MobileContactServiceImpl Logger: contact with id: %s was deleted.\n", phoneNumber);
            dao.deleteByPhoneNumber(phoneNumber);
        } catch (ContactNotFoundException e) {
            System.err.printf("MobileContactServiceImpl Logger: %s\n", e.getMessage());
            throw e;
        }
    }


    private MobileContact mapInsertDTOToContact(MobileContactInsertDTO dto) {   // MAPPING αντιστοιχούμε αυτά που έχει το DTO
        return new MobileContact(null, dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }

    private MobileContact mapUpdateDTOToContact(MobileContactUpdateDTO dto) {   // MAPPING αντιστοιχούμε αυτά που έχει το DTO
        return new MobileContact(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }
}
