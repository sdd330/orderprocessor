package com.github.sdd330.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "steps")
public class Step implements Serializable {
	
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
    @Column(name = "step", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private StepType step;
	
	@Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date startTime;
	
	@Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date endTime;
	
	@Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
	@Column(name = "step_status", nullable = true)
	private StepStatus stepStatus;
	
	@Getter
    @Setter
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}
