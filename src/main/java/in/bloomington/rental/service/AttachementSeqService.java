package in.bloomington.rental.service;

import in.bloomington.rental.model.AttachementSeq;

public interface AttachementSeqService
{
    public AttachementSeq get(int id);
    public void save(AttachementSeq attachementSeq);
}
