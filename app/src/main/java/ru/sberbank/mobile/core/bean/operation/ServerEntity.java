package ru.sberbank.mobile.core.bean.operation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import org.simpleframework.xml.Transient;

import ru.sberbank.mobile.core.network.ConnectorStatus;

/**
 * Базовая сущность-ответ от сервера. Содержит в себе {@link #mConnectorStatus} -- статус операции
 * с точки зрения клиента.
 *
 * Статусы операций с точки зрения сервера должны прописываться в абстрактных наследниках ServerEntity,
 * уже от этих наследников должны наследоваться семейства ответов от конкретного сервера.
 *
 * @author QuickNick
 */
public abstract class ServerEntity extends StatusedEntity {

    @Transient
    private ConnectorStatus mConnectorStatus;

    public ServerEntity() {
        mConnectorStatus = ConnectorStatus.SUCCESS;
    }

    @JsonIgnore
    public final ConnectorStatus getConnectorStatus() {
        return mConnectorStatus;
    }

    @JsonIgnore
    public final void setConnectorStatus(ConnectorStatus connectorStatus) {
        mConnectorStatus = connectorStatus;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServerEntity)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ServerEntity that = (ServerEntity) o;
        return mConnectorStatus == that.mConnectorStatus;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mConnectorStatus);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("super", super.toString())
                .add("mConnectorStatus", mConnectorStatus)
                .toString();
    }
}
