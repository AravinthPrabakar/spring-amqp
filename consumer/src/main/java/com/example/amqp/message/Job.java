package com.example.amqp.message;

import java.io.Serializable;

public class Job implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String type;
    protected String status;
    protected String data;
    protected Long sessionId;
    protected String priority;
    
    protected int customerId = -1;

    public Job(int id, String type, String status, String data,
               Long sessionId, String priority) {
        

        this.type = type;
        this.status = status;
        this.data = data;
        this.sessionId = sessionId;
        this.priority = priority;
    }

    public Job() {}


    public void release() {
        status = "UNOCCUPIED";
        sessionId = null;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getDataJson() {
        return data;
    }

    public void setDataJson(String data) {
        this.data = data;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("ID : "+getId()+" ");
    	buffer.append("Type : "+type+" ");
    	buffer.append("Status : "+status+" ");
    	buffer.append("Priority : "+priority+" ");
    	buffer.append("Data : "+data+" ");
    	return buffer.toString();
    }
}
