package lab4.task2.parts;

import java.util.ArrayList;
import java.util.List;

public class House implements IPart {
    private List<IPart> parts = new ArrayList<>();

    public void addPart(IPart part) {
        parts.add(part);
    }

    public int getBuiltParts() {
        return parts.size();
    }

    public List<IPart> getParts() {
        return parts;
    }

    @Override
    public String getName() {
        return "House";
    }
}
