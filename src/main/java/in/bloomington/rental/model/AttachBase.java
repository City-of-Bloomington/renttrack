package in.bloomington.rental.model;

import org.springframework.web.multipart.MultipartFile;

public class AttachBase
{

    private int           id;
    private MultipartFile file;
    private String        fileName;
    private String        type;
    private String        notes;

    public AttachBase()
    {
    }

    public AttachBase(int id, MultipartFile file, String fileName, String type, String notes)
    {
        this.id       = id;
        this.file     = file;
        this.fileName = fileName;
        this.type     = type;
        this.notes    = notes;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String val)
    {
        this.type = val;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getNotes()
    {
        return this.notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public MultipartFile getFile()
    {
        return this.file;
    }

    public void setFile(MultipartFile file)
    {
        this.file = file;
    }
}
