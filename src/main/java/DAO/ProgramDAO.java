package DAO;

import Enities.Program;

import java.util.List;

public interface ProgramDAO {
    public List<Program> getAllProgram();
    public List<Program> getProgramById();
    public List<Program> getProgramByName();
    public void addProgram(Program program);
    public void saveProgram(Program program);
    public void removeProgram(Program program);
    public void removeAllProgram(Program program);
    public void updateProgram(Program program);
}
