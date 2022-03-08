package fr.black.pm.tileEntities.custom.oreGenerator;

import net.minecraft.client.resources.model.ModelState;

import javax.annotation.Nullable;
import java.util.Objects;

public record ModelKey(boolean generating, boolean collecting, boolean actuallyGenerating,
                       @Nullable ModelState modelState) {

    public ModelKey(boolean generating, boolean collecting, boolean actuallyGenerating, @Nullable ModelState modelState) {
        this.generating = generating;
        this.collecting = collecting;
        this.actuallyGenerating = actuallyGenerating;
        this.modelState = modelState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelKey modelKey = (ModelKey) o;
        return generating == modelKey.generating && collecting == modelKey.collecting && actuallyGenerating == modelKey.actuallyGenerating && Objects.equals(modelState, modelKey.modelState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generating, collecting, actuallyGenerating, modelState);
    }
}
