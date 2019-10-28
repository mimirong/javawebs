create table OS_TECH_SERVICE_CONFIG
(
   CONFIG_NAME          VARCHAR(100) not null,
   CONFIG_TYPE          VARCHAR(40),
   CONFIG_VALUE         TEXT comment '一般为JSON',
   primary key (CONFIG_NAME)
);

insert into os_tech_service_config(config_name, config_type, config_value)
values ('UnitPricePerGBMonth', 'STORAGE', '1');

insert into os_tech_service_config(config_name, config_type, config_value)
values ('CpuOptions', 'COMPUTING', '["1核","2核","4核","8核"]');

insert into os_tech_service_config(config_name, config_type, config_value)
values ('MemoryOptions', 'COMPUTING', '["1G","2G","4G","8G","16G"]');

insert into os_tech_service_config(config_name, config_type, config_value)
values ('DiskOptions', 'COMPUTING', '["20G","40G","80G","120G"]');

insert into os_tech_service_config(config_name, config_type, config_value)
values ('BandwidthOptions', 'COMPUTING', '["1M","2M","4M","8M","10M"]');

