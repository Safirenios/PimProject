package gr.kotsovolos.integration.pim.message;

public class Message {
	
	private String actionType;
	private String eventType ;
	private String correlationId;
	private String id;
	private String catalogId;
	private String attachmentBlob;
	private String messageId;

	public Message setActionType(String actionType) {
		this.actionType = actionType;
		return this;
	}

	public Message setEventType(String eventType) {
		this.eventType = eventType;
		return this;
	}

	public Message setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
		return this;
	}

	public Message setId(String id) {
		this.id = id;
		return this;
	}

	public Message setCatalogId(String catalogId) {
		this.catalogId = catalogId;
		return this;
	}

	public Message setAttachmentBlob(String attachmentBlob) {
		this.attachmentBlob = attachmentBlob;
		return this;
	}

	public Message setMessageId(String messageId) {
		this.messageId = messageId;
		return this;
	}
	
	
	public String getActionType() {
		return actionType;
	}

	public String getEventType() {
		return eventType;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public String getId() {
		return id;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public String getAttachmentBlob() {
		return attachmentBlob;
	}

	public String getMessageId() {
		return messageId;
	}

}
