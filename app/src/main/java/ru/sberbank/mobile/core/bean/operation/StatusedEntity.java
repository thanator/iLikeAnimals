package ru.sberbank.mobile.core.bean.operation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import org.simpleframework.xml.Transient;

/**
 * Базовая сущность-ответ от модели, обладающая статусом.
 *
 * @author QuickNick
 */
public abstract class StatusedEntity {

    @Transient
    private boolean mHandled;

    public StatusedEntity() {
        mHandled = false;
    }

    @JsonIgnore
    public final boolean isHandled() {
        return mHandled;
    }

    @JsonIgnore
    public final void setHandled(boolean handled) {
        mHandled = handled;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatusedEntity)) {
            return false;
        }
        StatusedEntity that = (StatusedEntity) o;
        return mHandled == that.mHandled;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mHandled);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mHandled", mHandled)
                .toString();
    }

    @JsonIgnore
    public abstract boolean isSuccess();
}
