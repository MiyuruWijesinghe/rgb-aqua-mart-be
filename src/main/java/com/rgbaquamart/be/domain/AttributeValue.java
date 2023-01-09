package com.rgbaquamart.be.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.rgbaquamart.be.core.BaseEntity;

import lombok.Data;

@Entity
@Data
@Table(name = "attribute_value")
public class AttributeValue extends BaseEntity implements Serializable {

}