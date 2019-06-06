package com.jumpserver.sdk.v2.model;

import java.util.Date;

public class Asset {
    private String id;

    private String ip;

    private String hostname;

    private String cluster_name;

    private Integer port;

    private Boolean is_active;

    private Boolean is_connective;

    private String type;

    private String env;

    private String status;

    private String public_ip;

    private String remoteCardIp;

    private String cabinetNo;

    private Integer cabinetPos;

    private String number;

    private String vendor;

    private String model;

    private String sn;

    private String cpu_model;

    private Integer cpu_count;

    private Integer cpu_cores;

    private String memory;

    private String disk_total;

    private String disk_info;

    private String platform;

    private String os;

    private String os_version;

    private String os_arch;

    private String hostname_raw;

    private String created_by;

    private Date date_created;

    private String admin_user;

    private String cluster;

    private String comment;

    private String hardware_info;

    private String[] nodes;

    private String[] labels;

    private String protocol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getCluster_name() {
        return cluster_name;
    }

    public void setCluster_name(String cluster_name) {
        this.cluster_name = cluster_name;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Boolean getIs_connective() {
        return is_connective;
    }

    public void setIs_connective(Boolean is_connective) {
        this.is_connective = is_connective;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublic_ip() {
        return public_ip;
    }

    public void setPublic_ip(String public_ip) {
        this.public_ip = public_ip;
    }

    public String getRemoteCardIp() {
        return remoteCardIp;
    }

    public void setRemoteCardIp(String remoteCardIp) {
        this.remoteCardIp = remoteCardIp;
    }

    public String getCabinetNo() {
        return cabinetNo;
    }

    public void setCabinetNo(String cabinetNo) {
        this.cabinetNo = cabinetNo;
    }

    public Integer getCabinetPos() {
        return cabinetPos;
    }

    public void setCabinetPos(Integer cabinetPos) {
        this.cabinetPos = cabinetPos;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCpu_model() {
        return cpu_model;
    }

    public void setCpu_model(String cpu_model) {
        this.cpu_model = cpu_model;
    }

    public Integer getCpu_count() {
        return cpu_count;
    }

    public void setCpu_count(Integer cpu_count) {
        this.cpu_count = cpu_count;
    }

    public Integer getCpu_cores() {
        return cpu_cores;
    }

    public void setCpu_cores(Integer cpu_cores) {
        this.cpu_cores = cpu_cores;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getDisk_total() {
        return disk_total;
    }

    public void setDisk_total(String disk_total) {
        this.disk_total = disk_total;
    }

    public String getDisk_info() {
        return disk_info;
    }

    public void setDisk_info(String disk_info) {
        this.disk_info = disk_info;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getOs_arch() {
        return os_arch;
    }

    public void setOs_arch(String os_arch) {
        this.os_arch = os_arch;
    }

    public String getHostname_raw() {
        return hostname_raw;
    }

    public void setHostname_raw(String hostname_raw) {
        this.hostname_raw = hostname_raw;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getAdmin_user() {
        return admin_user;
    }

    public void setAdmin_user(String admin_user) {
        this.admin_user = admin_user;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getHardware_info() {
        return hardware_info;
    }

    public void setHardware_info(String hardware_info) {
        this.hardware_info = hardware_info;
    }

    public String[] getNodes() {
        return nodes;
    }

    public void setNodes(String[] nodes) {
        this.nodes = nodes;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
