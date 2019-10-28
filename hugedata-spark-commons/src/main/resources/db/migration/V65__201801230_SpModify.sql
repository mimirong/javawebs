/*==============================================================*/
/* Table: sp_specification                                    */
/*==============================================================*/
alter TABLE sp_specification modify SPEC_BRIEF varchar(255) COMMENT '规格简介，限字100个';
