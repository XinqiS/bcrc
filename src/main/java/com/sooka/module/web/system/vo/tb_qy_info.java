package com.sooka.module.web.system.vo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_qy_info")
public class tb_qy_info {
	
	 @Id
	 private String qy_id;
	    private String zh_id;
		private Integer qy_spzt;
	    private String qy_sftj;
	    private String qy_qymc;
	    private String qy_sshy;
	    private String qy_jjlx;
	    private String qy_qygm;
	    private String qy_qyfl;
	    private String qy_qywz;
	    private String qy_lxr;
	    private String qy_lxdh;
	    private String qy_yx;
	    private String qy_xxdz;
	    private String qy_qyjl;
		private String qy_fbsc;
	    private String qy_file_zz;
	    private String qy_file_logo;
		public String getQy_id() {
			return qy_id;
		}
		public void setQy_id(String qy_id) {
			this.qy_id = qy_id;
		}
		public String getZh_id() {
			return zh_id;
		}
		public void setZh_id(String zh_id) {
			this.zh_id = zh_id;
		}

	public Integer getQy_spzt() {
		return qy_spzt;
	}

	public void setQy_spzt(Integer qy_spzt) {
		this.qy_spzt = qy_spzt;
	}

	public String getQy_sftj() {
		return qy_sftj;
	}

	public void setQy_sftj(String qy_sftj) {
		this.qy_sftj = qy_sftj;
	}

	public String getQy_qymc() {
		return qy_qymc;
	}

	public void setQy_qymc(String qy_qymc) {
		this.qy_qymc = qy_qymc;
	}

	public String getQy_sshy() {
		return qy_sshy;
	}

	public void setQy_sshy(String qy_sshy) {
		this.qy_sshy = qy_sshy;
	}

	public String getQy_jjlx() {
		return qy_jjlx;
	}

	public void setQy_jjlx(String qy_jjlx) {
		this.qy_jjlx = qy_jjlx;
	}

	public String getQy_qygm() {
		return qy_qygm;
	}

	public void setQy_qygm(String qy_qygm) {
		this.qy_qygm = qy_qygm;
	}



	public String getQy_qyfl() {
			return qy_qyfl;
		}
		public void setQy_qyfl(String qy_qyfl) {
			this.qy_qyfl = qy_qyfl;
		}
		public String getQy_qywz() {
			return qy_qywz;
		}
		public void setQy_qywz(String qy_qywz) {
			this.qy_qywz = qy_qywz;
		}
		public String getQy_lxr() {
			return qy_lxr;
		}
		public void setQy_lxr(String qy_lxr) {
			this.qy_lxr = qy_lxr;
		}
		public String getQy_lxdh() {
			return qy_lxdh;
		}
		public void setQy_lxdh(String qy_lxdh) {
			this.qy_lxdh = qy_lxdh;
		}
		public String getQy_yx() {
			return qy_yx;
		}
		public void setQy_yx(String qy_yx) {
			this.qy_yx = qy_yx;
		}
		public String getQy_xxdz() {
			return qy_xxdz;
		}
		public void setQy_xxdz(String qy_xxdz) {
			this.qy_xxdz = qy_xxdz;
		}
		public String getQy_qyjl() {
			return qy_qyjl;
		}
		public void setQy_qyjl(String qy_qyjl) {
			this.qy_qyjl = qy_qyjl;
		}
		public String getQy_file_zz() {
			return qy_file_zz;
		}
		public void setQy_file_zz(String qy_file_zz) {
			this.qy_file_zz = qy_file_zz;
		}
		public String getQy_file_logo() {
			return qy_file_logo;
		}
		public void setQy_file_logo(String qy_file_logo) {
			this.qy_file_logo = qy_file_logo;
		}
		public String getQy_fbsc() {return qy_fbsc;	}
		public void setQy_fbsc(String qy_fbsc) {this.qy_fbsc = qy_fbsc;}
		@Override
		public String toString() {
			return "tb_qy_info [qy_id=" + qy_id + ", zh_id=" + zh_id
					+ ", qy_spzt=" + qy_spzt + ", qy_sftj=" + qy_sftj
					+ ", qy_qymc=" + qy_qymc + ", qy_sshy=" + qy_sshy
					+ ", qy_jjlx=" + qy_jjlx + ", qy_qygm=" + qy_qygm
					+ ", qy_qyfl=" + qy_qyfl + ", qy_qywz=" + qy_qywz
					+ ", qy_lxr=" + qy_lxr + ", qy_lxdh=" + qy_lxdh
					+ ", qy_yx=" + qy_yx + ", qy_xxdz=" + qy_xxdz
					+ ", qy_qyjl=" + qy_qyjl + ", qy_file_zz=" + qy_file_zz
					+ ", qy_fbsc=" + qy_fbsc
					+ ", qy_file_logo=" + qy_file_logo + "]";
		}
}