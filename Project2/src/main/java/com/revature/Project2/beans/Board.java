package com.revature.Project2.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SCRUM_BOARD")
public class Board implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="B_ID")
	@SequenceGenerator(sequenceName="BOARD_SEQ", name="BOARD_SEQ")
	@GeneratedValue(generator="BOARD_SEQ", strategy=GenerationType.SEQUENCE)
	private int bid;
	
	@Column(name="BOARD_NAME", unique=true)
	private String boardName;
	
	//swimlanes
	@OneToMany(fetch=FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Swimlane> swimlanes;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SCRUM_MASTER")
	private User scrumMaster;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SCRUM_PRODUCT_OWNER")
	private User scrumProductOwner;
	
	//list of all team members
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="TEAM_MEMBER",
			joinColumns= @JoinColumn(name="BOARD_ID"),
			inverseJoinColumns= @JoinColumn(name="USER_ID"))
	private List<User> scrumTeam;
	
	//map for the burndown chart
	@ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "HISTORY")
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
	private Map<Date, Integer> burnDown;

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public User getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(User scrumMaster) {
		this.scrumMaster = scrumMaster;
	}
	
	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public Map<Date, Integer> getBurnDown() {
		return burnDown;
	}

	public void setBurnDown(Map<Date, Integer> burnDown) {
		this.burnDown = burnDown;
	}

	public List<Swimlane> getSwimlanes() {
		return swimlanes;
	}

	public void setSwimlanes(List<Swimlane> swimlanes) {
		this.swimlanes = swimlanes;
	}

	public User getScrumProductOwner() {
		return scrumProductOwner;
	}

	public void setScrumProductOwner(User scrumProductOwner) {
		this.scrumProductOwner = scrumProductOwner;
	}

	public List<User> getScrumTeam() {
		return scrumTeam;
	}

	public void setScrumTeam(List<User> scrumTeam) {
		this.scrumTeam = scrumTeam;
	}

	public Board(int bid, String boardName, List<Swimlane> swimlanes, User scrumMaster, User scrumProductOwner, List<User> scrumTeam,
			Map<Date, Integer> burnDown) {
		super();
		this.bid = bid;
		this.boardName = boardName;
		this.swimlanes = swimlanes;
		this.scrumMaster = scrumMaster;
		this.scrumProductOwner = scrumProductOwner;
		this.scrumTeam = scrumTeam;
		this.burnDown = burnDown;
	}

	public Board(String boardName, List<Swimlane> swimlanes, User scrumMaster, User scrumProductOwner, List<User> scrumTeam,
			Map<Date, Integer> burnDown) {
		super();
		this.boardName = boardName;
		this.swimlanes = swimlanes;
		this.scrumMaster = scrumMaster;
		this.scrumProductOwner = scrumProductOwner;
		this.scrumTeam = scrumTeam;
	}
	
	public Board() {
		super();
	}
	
	
}
