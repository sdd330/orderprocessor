package com.github.sdd330.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.github.sdd330.domain.StepType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
    @Setter
    @Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date orderDate;
	
	@Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
	@Column(name = "order_status", nullable = true)
	private OrderStatus orderStatus;
	
	@Getter
    @Setter
    @Column(name = "current_step", nullable = true)
	@Enumerated(EnumType.ORDINAL)
	private StepType currentStep;
	
	@Getter
    @Setter
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private Collection<Step> orderSteps = new ArrayList<Step>();
	
}
