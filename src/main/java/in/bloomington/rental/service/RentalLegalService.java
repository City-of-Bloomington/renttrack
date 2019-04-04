package in.bloomington.rental.service;

import in.bloomington.rental.model.RentalLegal;

public interface RentalLegalService
{
    public RentalLegal get(int id);
    public void save(RentalLegal val);
}
