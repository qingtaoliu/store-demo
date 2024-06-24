package com.cy.store.entity;

import java.util.Objects;

public class District extends BaseEntity {

    private Integer id;
    private String name;
    private String code;
    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof District district)) return false;
        return Objects.equals(id, district.id) && Objects.equals(name, district.name) && Objects.equals(code, district.code) && Objects.equals(parentId, district.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, parentId);
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
