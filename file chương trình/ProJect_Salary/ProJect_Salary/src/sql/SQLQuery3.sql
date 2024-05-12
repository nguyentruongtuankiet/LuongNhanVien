	USE [master]
GO
/****** Object:  Database [QuanLyLuongSanPham]    Script Date: 7/5/2016 11:35:56 PM ******/
CREATE DATABASE [QuanLyLuongSanPham]
 CONTAINMENT = NONE
 go
ALTER DATABASE [QuanLyLuongSanPham] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyLuongSanPham].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET RECOVERY FULL 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyLuongSanPham] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QuanLyLuongSanPham', N'ON'
GO
USE [QuanLyLuongSanPham]
GO
/****** Object:  Table [dbo].[LuongCongNhan]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LuongCongNhan](
    [MaLuong] [int]IDENTITY (1,1) NOT NULL,
	[MaCN] [nvarchar](255) NOT NULL,
	[TenCN] [nvarchar](255) NOT NULL,
	[SoSPLamDuoc] [int] NOT NULL,
	[PhuCap] [float] NOT NULL,
	[ThangLuong] [int] NOT NULL,
	[NamLuong] [int] NOT NULL,
	[TongLuong] [float] NOT NULL,
	
) 

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Congnhan]  Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Congnhan](
	[MaCN] [nvarchar](255) NOT NULL,
	[MaPB] [nvarchar](255) NOT NULL,
	[TenCN] [nvarchar](255) NOT NULL,
	[Sđt] [nvarchar](255) NOT NULL,
	[DiaChi] [nvarchar](255) NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[NgaySinh] [date] NOT NULL,
) 

GO
/****** Object:  Table [dbo].[PhieuCong_CN]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuCong_CN](
    [MaCa] [nvarchar](255) NOT NULL,
	[TenCa] [nvarchar](255) NOT NULL,
	[MaCN] [nvarchar](255) NOT NULL,
	[TenCN] [nvarchar](255) NOT NULL,
	[SoSPChamCong] [int] NOT NULL,
	[MaCĐ] [nvarchar](255) NOT NULL,
    [TenCĐ] [nvarchar](255) NOT NULL,
	[NgayCham] [date] NOT NULL,
	[DiLam] [bit] NOT NULL,
	[NghiPhep] [bit] NOT NULL,
	[TangCa] [bit] NOT NULL,

	
)
GO
/****** Object:  Table [dbo].[CaLamViec]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLamViec](
	[MaCa] [nvarchar](255) NOT NULL,
	[TenCa] [nvarchar](255) NOT NULL,
	[GioLam] [nvarchar](255) NOT NULL,
)

GO
/****** Object:  Table [dbo].[PhanCong]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanCong](
	
	[MaCĐ] [nvarchar](255) NOT NULL,
	[MaCN] [nvarchar](255) NOT NULL,
	[TenCN] [nvarchar](255) NOT NULL,
	[TenCD] [nvarchar](255) NOT NULL,
	[MaSP] [nvarchar](255) NOT NULL,
	[TenSP] [nvarchar](255) NOT NULL,

)

GO
/****** Object:  Table [dbo].[PhongBan]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhongBan](
	[MaPB] [nvarchar](255)  NOT NULL,
	[TenPB] [nvarchar](255) NOT NULL,
 ) 
GO
/****** Object:  Table [dbo].[TrinhDo]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TrinhDo](
	[MaTrinhDo] [nvarchar](255)  NOT NULL,
	[TenTrinhDo] [nvarchar](255) NOT NULL,
	[HeSoLuong] [float] NOT NULL,
 ) 
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVienHanhChanh](
	[MaNV] [nvarchar](255) NOT NULL,
	[MaPB] [nvarchar](255) NOT NULL,
	[TenNV] [nvarchar](255) NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[NgayThamGiaCT] [date] NOT NULL,
	[DiaChi] [nvarchar](255) NOT NULL,
	[Sđt] [nvarchar](255) NULL,
	[GioiTinh] [bit] NOT NULL,
	[MaTrinhDo] [nvarchar](255) NOT NULL,
) 

GO
/****** Object:  Table [dbo].[LuongNhanVienHanhChanh]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LuongNhanVienHanhChanh](
    [MaLuong] [int]IDENTITY (1,1) NOT NULL,
	[MaNV] [nvarchar](255) NOT NULL,
	[TenNV] [nvarchar](255) NOT NULL,
	[LuongCoBan] [float] NOT NULL,
	[SoNgayTangCa] [int] NOT NULL,
	[SoNgayLamDuoc] [int] NOT NULL,
	[HeSoLuong] [float] NOT NULL,
	[PhuCap] [float] NOT NULL,
	[ThangLuong] [int] NOT NULL,
	[NamLuong] [int] NOT NULL,
    [TongLuong] [float] NOT NULL,) 

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PhieuChamCong_NV]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PhieuChamCong_NV](
	[MaPhieu] [int]IDENTITY (1,1) NOT NULL,
	[MaNV] [nvarchar](255) NOT NULL,
	[TenNV] [nvarchar](255) NOT NULL,
	[NgayCham] [date] NOT NULL,
	[DiLam] [bit] NOT NULL,
	[NghiPhep] [bit] NOT NULL,
	[TangCa] [bit] NOT NULL,
)
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSP] [nvarchar](255) NOT NULL,
	[TenSP] [nvarchar](255) NOT NULL,
	[ThuongHieu] [nvarchar](255) NOT NULL,
	[DonGia] [float] NOT NULL,
	[SoLuong] [int] NOT NULL,
	[DonViTinh] [nvarchar](255) NOT NULL,
	[Anh] [nvarchar](255) NOT NULL
)

GO
/****** Object:  Table [dbo].[CongDoan]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongDoan](
	[MaCĐ] [nvarchar](255)  NOT NULL,
	[MaSP] [nvarchar](255) NOT NULL,
	[TenSP] [nvarchar](255) NOT NULL,
	[TenCĐ] [nvarchar](255) NOT NULL,
	[DonGiaCĐ] [float] NOT NULL, 
    [SoLuong] [int] NOT NULL,
    [MaRangBuoc] int NOT NULL,
	[TrangThai] [bit] NOT NULL,
) 

GO


/* Tạo khóa chính */
ALTER TABLE [dbo].[LuongCongNhan]  ADD CONSTRAINT [FK_LuongCongNhan] PRIMARY KEY (MaLuong)
GO

ALTER TABLE [dbo].[TrinhDo]  ADD CONSTRAINT [FK_TrinhDo] PRIMARY KEY (MaTrinhDo)
GO

ALTER TABLE [dbo].[CongNhan]  ADD CONSTRAINT [FK_CongNhan] PRIMARY KEY (MaCN)
GO


ALTER TABLE [dbo].[CaLamViec]  ADD CONSTRAINT [FK_CaLamViec] PRIMARY KEY (MaCa)
GO


ALTER TABLE [dbo].[PhongBan]  ADD CONSTRAINT [FK_PhongBan] PRIMARY KEY (MaPB)
GO

ALTER TABLE [dbo].[NhanVienHanhChanh]  ADD CONSTRAINT [FK_NhanVienHanhChanh] PRIMARY KEY (MaNV)
GO

ALTER TABLE [dbo].[LuongNhanVienHanhChanh]  ADD CONSTRAINT [FK_LuongNhanVienHanhChanh] PRIMARY KEY (MaLuong)
GO

ALTER TABLE [dbo].[PhieuChamCong_NV]  ADD CONSTRAINT [FK_PhieuChamCong_NV] PRIMARY KEY (MaPhieu)
GO

ALTER TABLE [dbo].[SanPham]  ADD CONSTRAINT [FK_SanPham] PRIMARY KEY (MaSP)
GO


ALTER TABLE [dbo].[CongDoan]  ADD CONSTRAINT [FK_CongDoan] PRIMARY KEY (MaCĐ)
GO

/*Tạo khóa ngoại */

ALTER TABLE [dbo].[LuongCongNhan]  WITH CHECK ADD  CONSTRAINT [LuongCongNhan_CongNhan] FOREIGN KEY([MaCN])
REFERENCES [dbo].[CongNhan] ([MaCN])
GO

ALTER TABLE [dbo].[NhanVienHanhChanh]  WITH CHECK ADD  CONSTRAINT [NhanVienHanhChanh_TrinhDo] FOREIGN KEY([MaTrinhDo])
REFERENCES [dbo].[trinhDo] ([MaTrinhDo])
GO

ALTER TABLE [dbo].[PhieuCong_CN]  WITH CHECK ADD  CONSTRAINT [FK_PhieuCong_CN_CongNhan] FOREIGN KEY([MaCN])
REFERENCES [dbo].[CongNhan] ([MaCN])
GO

ALTER TABLE [dbo].[PhieuCong_CN]  WITH CHECK ADD  CONSTRAINT [FK_PhieuCong_CN_CaLamViec] FOREIGN KEY([MaCa])
REFERENCES [dbo].[CaLamViec] ([MaCa])
GO

ALTER TABLE [dbo].[PhieuCong_CN]  WITH CHECK ADD  CONSTRAINT [FK_PhieuCong_CN_CongDoan] FOREIGN KEY([MaCĐ])
REFERENCES [dbo].[CongDoan] ([MaCĐ])
GO

ALTER TABLE [dbo].[PhanCong]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_CongDoan] FOREIGN KEY([MaCĐ])
REFERENCES [dbo].[CongDoan] ([MaCĐ])
GO

ALTER TABLE [dbo].[PhanCong]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_CongNhan] FOREIGN KEY([MaCN])
REFERENCES [dbo].[CongNhan] ([MaCN])
GO


ALTER TABLE [dbo].[CongNhan]  WITH CHECK ADD  CONSTRAINT [FK_CongNhan_PhongBan] FOREIGN KEY([MaPB])
REFERENCES [dbo].[PhongBan] ([MaPB])
GO

ALTER TABLE [dbo].[NhanVienHanhChanh]  WITH CHECK ADD  CONSTRAINT [FK_NhanVienHanhChanh_PhongBan] FOREIGN KEY([MaPB])
REFERENCES [dbo].[PhongBan] ([MaPB])
GO

ALTER TABLE [dbo].[LuongNhanVienHanhChanh]  WITH CHECK ADD  CONSTRAINT [FK_LuongNhanVienHanhChanh_NhanVienHanhChanh] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVienHanhChanh] ([MaNV])
GO

ALTER TABLE [dbo].[PhieuChamCong_NV]  WITH CHECK ADD  CONSTRAINT [FK_PhieuChamCong_NV_NhanVienHanhChanh] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVienHanhChanh] ([MaNV])
GO

ALTER TABLE [dbo].[CongDoan]  WITH CHECK ADD  CONSTRAINT [FK_CongDoan_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO 


INSERT [dbo].[trinhDo] ([MaTrinhDo], [TenTrinhDo], [HeSoLuong]) VALUES (N'ĐH', N'Đại Học',2.34)
INSERT [dbo].[trinhDo] ([MaTrinhDo], [TenTrinhDo], [HeSoLuong]) VALUES (N'TH', N'Trung Học',1.86)
INSERT [dbo].[trinhDo] ([MaTrinhDo], [TenTrinhDo], [HeSoLuong]) VALUES (N'CĐ', N'Cao Đẳng',2.10)
GO

INSERT [dbo].[PhongBan] ([MaPB], [TenPB]) VALUES (N'QL', N'Quản Lí')
INSERT [dbo].[PhongBan] ([MaPB], [TenPB]) VALUES (N'HC', N'Hành Chính')
INSERT [dbo].[PhongBan] ([MaPB], [TenPB]) VALUES (N'SX', N'Sản Xuất')
INSERT [dbo].[PhongBan] ([MaPB], [TenPB]) VALUES (N'KT', N'Kế Toán')
GO
INSERT [dbo].[Congnhan] ([MaCN], [MaPB], [TenCN], [Sđt], [DiaChi], [GioiTinh], [NgaySinh]) VALUES (N'CN001', N'SX', N'Nguyễn Trường Tuấn Kiệt',N'0786561865',N'Đồng Nai',1,  CAST(N'2002-10-27' AS Date))
INSERT [dbo].[Congnhan] ([MaCN], [MaPB], [TenCN], [Sđt], [DiaChi], [GioiTinh], [NgaySinh]) VALUES (N'CN002', N'KT', N'Nguyễn Anh Tuấn',N'0702345123',N'TP.HCM',1,  CAST(N'2000-11-17' AS Date))
INSERT [dbo].[Congnhan] ([MaCN], [MaPB], [TenCN], [Sđt], [DiaChi], [GioiTinh], [NgaySinh]) VALUES (N'CN003', N'QL', N'Nguyễn Văn Phú',N'0706789654',N'Vũng Tàu',0,  CAST(N'2002-06-18' AS Date))
INSERT [dbo].[Congnhan] ([MaCN], [MaPB], [TenCN], [Sđt], [DiaChi], [GioiTinh], [NgaySinh]) VALUES (N'CN004', N'SX', N'Trần Thu Hạnh',N'0786561545',N'Bắc Giang',1,  CAST(N'2002-10-27' AS Date))
INSERT [dbo].[Congnhan] ([MaCN], [MaPB], [TenCN], [Sđt], [DiaChi], [GioiTinh], [NgaySinh]) VALUES (N'CN005', N'HC', N'Nguyễn Minh Dũng',N'0702345345',N'Bình Dương',1,  CAST(N'2000-11-17' AS Date))
INSERT [dbo].[Congnhan] ([MaCN], [MaPB], [TenCN], [Sđt], [DiaChi], [GioiTinh], [NgaySinh]) VALUES (N'CN006', N'KT', N'Hà Bảo Anh',N'0706434543',N'Hà Nội',0,  CAST(N'2002-06-10' AS Date))
INSERT [dbo].[Congnhan] ([MaCN], [MaPB], [TenCN], [Sđt], [DiaChi], [GioiTinh], [NgaySinh]) VALUES (N'CN007', N'SX', N'Nguyễn Minh Hiếu',N'0786989767',N'Hải Phòng',1,  CAST(N'2002-1-27' AS Date))
GO

INSERT [dbo].[NhanVienHanhChanh] ([MaNV], [MaPB], [TenNV], [NgaySinh], [NgayThamGiaCT], [DiaChi], [Sđt], [GioiTinh], [MaTrinhDo]) VALUES (N'NV01', N'HC', N'Nguyễn Anh Tuấn',CAST(N'2000-12-24' AS Date),CAST(N'2022-10-03' AS Date),N'Hà Nội',N'0392887340',1,N'TH' )
INSERT [dbo].[NhanVienHanhChanh] ([MaNV], [MaPB], [TenNV], [NgaySinh], [NgayThamGiaCT], [DiaChi], [Sđt], [GioiTinh], [MaTrinhDo]) VALUES (N'NV02', N'QL', N'Phạm Thị Ngọc Hương',CAST(N'2002-05-24' AS Date),CAST(N'2022-10-02' AS Date),N'Tiền Giang',N'0207123456',0,N'CĐ' )
INSERT [dbo].[NhanVienHanhChanh] ([MaNV], [MaPB], [TenNV], [NgaySinh], [NgayThamGiaCT], [DiaChi], [Sđt], [GioiTinh], [MaTrinhDo]) VALUES (N'NV03', N'SX', N'Nguyễn Thị Hoàng Khánh',CAST(N'1998-11-03' AS Date),CAST(N'2022-09-21' AS Date),N'Long An',N'0903412123',0,N'ĐH' )
INSERT [dbo].[NhanVienHanhChanh] ([MaNV], [MaPB], [TenNV], [NgaySinh], [NgayThamGiaCT], [DiaChi], [Sđt], [GioiTinh], [MaTrinhDo]) VALUES (N'NV04', N'HC', N'Trần Thiện Đạt',CAST(N'1999-04-23' AS Date),CAST(N'2022-10-03' AS Date),N'Hải Phòng',N'0908659067',1,N'TH' )
INSERT [dbo].[NhanVienHanhChanh] ([MaNV], [MaPB], [TenNV], [NgaySinh], [NgayThamGiaCT], [DiaChi], [Sđt], [GioiTinh], [MaTrinhDo]) VALUES (N'NV05', N'QL', N'Phạm Khả Chinh',CAST(N'2002-08-24' AS Date),CAST(N'2022-10-01' AS Date),N'An Giang',N'0205643456',0,N'CĐ' )
GO

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-01' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-01' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-01' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-01' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-01' AS Date),1,0,0)


INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-02' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-02' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-02' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-02' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-02' AS Date),1,0,0)


INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-03' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-03' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-03' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-03' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-03' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-04' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-04' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-04' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-04' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-04' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-05' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-05' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-05' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-05' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-05' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-06' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-06' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-06' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-06' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-06' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-07' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-07' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-07' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-07' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-07' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-08' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-08' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-08' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-08' AS Date),0,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-08' AS Date),1,0,1)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-09' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-09' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-09' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-09' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-09' AS Date),1,0,1)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-10' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-10' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-10' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-10' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-10' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-11' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-11' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-11' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-11' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-11' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-12' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-12' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-12' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-12' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-12' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-13' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-13' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-13' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-13' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-13' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-14' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-14' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-14' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-14' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-14' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-15' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-15' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-15' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-15' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-15' AS Date),1,0,1)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-16' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-16' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-16' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-16' AS Date),0,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-16' AS Date),0,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-17' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-17' AS Date),0,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-17' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-17' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-17' AS Date),0,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-18' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-18' AS Date),0,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-18' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-18' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-18' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-19' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-19' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-19' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-19' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-19' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-20' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-20' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-20' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-20' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-20' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-21' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-21' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-21' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-21' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-21' AS Date),1,0,1)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-22' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-22' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-22' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-22' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-22' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-23' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-23' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-23' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-23' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-23' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-11-24' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-11-24' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-11-24' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-11-24' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-11-24' AS Date),1,0,0)


INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-01' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-01' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-01' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-01' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-01' AS Date),1,0,1)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-02' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-02' AS Date),0,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-02' AS Date),0,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-02' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-02' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-03' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-03' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-03' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-03' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-03' AS Date),0,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-04' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-04' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-04' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-04' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-04' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-05' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-05' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-05' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-05' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-05' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-06' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-06' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-06' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-06' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-06' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-07' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-07' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-07' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-07' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-07' AS Date),1,0,1)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-08' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-08' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-08' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-08' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-08' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-09' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-09' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-09' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-09' AS Date),1,0,1)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-09' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-10' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-10' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-10' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-10' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-10' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-11' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-11' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-11' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-11' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-11' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-12' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-12' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-12' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-12' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-12' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-14' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-14' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-14' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-14' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-14' AS Date),1,0,0)

INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV01', N'Nguyễn Anh Tuấn',CAST(N'2022-12-15' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV02', N'Phạm Thị Ngọc Hương',CAST(N'2022-12-15' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV03', N'Nguyễn Thị Hoàng Khánh',CAST(N'2022-12-15' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV04', N'Trần Thiện Đạt',CAST(N'2022-12-15' AS Date),1,0,0)
INSERT [dbo].[PhieuChamCong_NV] ([MaNV], [TenNV], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'NV05', N'Phạm Khả Chinh',CAST(N'2022-12-15' AS Date),1,0,0)


GO


INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh], [Anh]) VALUES (N'SP01', N'PepSi Zero', N'PepsiCo',144000,100,N'Thùng', '\\src\image\\anhsp.jpg' )
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP02', N'Cocacola vị orginal', N'Coca-Cola',140000,100,N'Thùng','\\src\image\\anhsp2.jpg' )
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP03', N'Sting vị dâu', N'Coca-Cola',160000,200,N'Thùng','\\src\image\\anhsp3.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP04', N'Nước khoáng i-on Pocari Sweat', N'Warrior',9000,2500,N'Chai','\\src\image\\anhsp4.jpg' )
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP05', N'Nước tăng lực Warrior hương nho', N'Coca-Cola',10000,2500,N'Chai','\\src\image\\anhsp5.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP06', N'Nước tinh khiết Aquafina', N'Aquafina',5000,150,N'Thùng','\\src\image\\anhsp6.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP07', N'Nước tinh khiết Dasani', N'Danasi',15000,100,N'Thùng','\\src\image\\anhsp7.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP08', N'Sữa trái cây Nutriboost hương cam', N'Nutriboost',35000,120,N'Thùng','\\src\image\\anhsp8.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP09', N'Nước sữa trái cây TH True Milk cam', N'TH True Juice Milk',17000,120,N'Thùng','\\src\image\\anhsp9.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP10', N'Nước ngọt Fanta hương xá xị', N'Fanta',15000,120,N'Thùng','\\src\image\\anhsp10.jpg')
GO

INSERT [dbo].[CaLamViec] ([MaCa], [TenCa],[GioLam]) VALUES (N'CA01', N'Sáng',N'7h-11h')
INSERT [dbo].[CaLamViec] ([MaCa], [TenCa],[GioLam]) VALUES (N'CA02', N'Chiều',N'13h-17h')
INSERT [dbo].[CaLamViec] ([MaCa], [TenCa],[GioLam]) VALUES (N'CA03', N'Tối',N'18h-23h')
GO


INSERT [dbo].[CongDoan] ([MaCĐ], [MaSP],[TenSP], [TenCĐ], [DonGiaCĐ], [SoLuong], [MaRangBuoc],[TrangThai]) VALUES (N'CD01SP01', N'SP01', N'PepSi Zero', N'Chuẩn bị nước',800,0,1,0)


INSERT [dbo].[CongDoan] ([MaCĐ], [MaSP],[TenSP], [TenCĐ], [DonGiaCĐ], [SoLuong], [MaRangBuoc],[TrangThai]) VALUES (N'CD01SP02', N'SP02', N'Cocacola vị orginal', N'Chuẩn bị nước',800,0,1,0)


INSERT [dbo].[CongDoan] ([MaCĐ], [MaSP],[TenSP], [TenCĐ], [DonGiaCĐ], [SoLuong], [MaRangBuoc],[TrangThai]) VALUES (N'CD01SP03', N'SP03', N'Sting vị dâu', N'Chuẩn bị nước',800,0,1,0 )


GO

INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP01', N'CN002', N'Nguyễn Anh Tuấn', N'Chuẩn bị nước',N'SP01',N'PepSi Zero' )
INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP01', N'CN004', N'Trần Thu Hạnh', N'Chuẩn bị nước',N'SP01',N'PepSi Zero' )
INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP01', N'CN003', N'Nguyễn Văn Phú', N'Chuẩn bị nước',N'SP01',N'PepSi Zero' )
INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP01', N'CN007', N'Nguyễn Minh Hiếu', N'Chuẩn bị nước',N'SP01',N'PepSi Zero' )


INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP02', N'CN001', N'Nguyễn Trường Tuấn Kiệt', N'Chuẩn bị nước',N'SP02',N'Cocacola vị orginal' )
INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP02', N'CN005', N'Nguyễn Minh Dũng', N'Chuẩn bị nước',N'SP02',N'Cocacola vị orginal' )
INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP02', N'CN007', N'Nguyễn Minh Hiếu', N'Chuẩn bị nước',N'SP02',N'Cocacola vị orginal' )
INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP02', N'CN002', N'Nguyễn Anh Tuấn', N'Chuẩn bị nước',N'SP02',N'Cocacola vị orginal' )

INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP03', N'CN001', N'Nguyễn Trường Tuấn Kiệt', N'Chuẩn bị nước',N'SP03',N'PepSi Zero' )
INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP03', N'CN004', N'Trần Thu Hạnh', N'Chuẩn bị nước',N'SP03',N'PepSi Zero' )
INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP03', N'CN003', N'Nguyễn Văn Phú', N'Chuẩn bị nước',N'SP03',N'PepSi Zero' )
INSERT [dbo].[PhanCong] ([MaCĐ], [MaCN],[TenCN], [TenCD], [MaSP], [TenSP]) VALUES (N'CD01SP03', N'CN006', N'Hà Bảo Anh', N'Chuẩn bị nước',N'SP03',N'PepSi Zero' )
GO

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-03' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-03' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-03' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-03' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-04' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-04' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-04' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-04' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-05' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-05' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-05' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-05' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-06' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-06' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-06' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-06' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-07' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-07' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-07' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-07' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-08' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-08' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-08' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-08' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-09' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-09' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-09' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-09' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-10' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-10' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-10' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-10' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-11' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-11' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-11' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-11' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-12' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-12' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-12' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-12' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-13' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-13' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-13' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-13' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-14' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-14' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-14' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-14' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-15' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-15' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-15' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-15' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-16' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-16' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-16' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-16' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-17' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-17' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-17' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-17' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-18' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-18' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-18' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-18' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-19' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-19' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-19' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-19' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-20' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN004',N'Nguyễn Thu Hạnh',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-20' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN003', N'Nguyễn Văn Phú',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-20' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA01', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP01',N'Chuẩn bị nước',CAST(N'2022-11-20' AS Date),1,0,0)


INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-03' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-03' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-03' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-03' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-04' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-04' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-04' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-04' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-05' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-05' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-05' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-05' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-06' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-06' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-06' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-06' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-07' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-07' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-07' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-07' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-08' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-08' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-08' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-08' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-09' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-09' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-09' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-09' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-10' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-10' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-10' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-10' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-11' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-11' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-11' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-11' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-12' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-12' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-12' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-12' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-13' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-13' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-13' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-13' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-14' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-14' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-14' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-14' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-15' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-15' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-15' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-15' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-16' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-16' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-16' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-16' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-17' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-17' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-17' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-17' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-18' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-18' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-18' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-18' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-19' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-19' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-19' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-19' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN001', N'Nguyễn Trường Tuấn Kiệt',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-20' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN005', N'Nguyễn Minh Dũng',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-20' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN007', N'Nguyễn Minh Hiếu',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-20' AS Date),1,0,0)
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Sáng', N'CN002', N'Nguyễn Anh Tuấn',30,N'CD01SP02',N'Chuẩn bị nước',CAST(N'2022-11-20' AS Date),1,0,0)

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN001', N'Nguyễn Trường Tuấn Kiệt',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0 )
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN004', N'Trần Thu Hạnh',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0 )
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN003', N'Nguyễn Văn Phú',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0 )
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN006', N'Hà Bảo Anh',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0 )

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN001', N'Nguyễn Trường Tuấn Kiệt',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0 )
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN004', N'Trần Thu Hạnh',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0 )
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN003', N'Nguyễn Văn Phú',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0 )
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN006', N'Hà Bảo Anh',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-01' AS Date),1,0,0 )

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN001', N'Nguyễn Trường Tuấn Kiệt',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0 )
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN004', N'Trần Thu Hạnh',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0 )
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN003', N'Nguyễn Văn Phú',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0 )
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN006', N'Hà Bảo Anh',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-02' AS Date),1,0,0 )

INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN001', N'Nguyễn Trường Tuấn Kiệt',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-03' AS Date),1,0,0 )
INSERT [dbo].[PhieuCong_CN] ([MaCa], [TenCa],[MaCN], [TenCN], [SoSPChamCong], [MaCĐ],[TenCĐ], [NgayCham],[DiLam], [NghiPhep], [TangCa]) VALUES (N'CA02', N'Chiều', N'CN004', N'Trần Thu Hạnh',60,N'CD01SP03',N'Chuẩn bị nước',CAST(N'2022-11-03' AS Date),1,0,0 )
/**=========================================================================================================================================================================================================================================================================================



*/


select CONCAT('SP', RIGHT(CONCAT('00',ISNULL(right(max(maSP),2),0) + 1),2)) from SanPham where maSP like  'SP%'


USE [master]

GO
ALTER DATABASE [QuanLyLuongSanPham] SET  READ_WRITE 
GO

--DROP DATABASE [QuanLyLuongSanPham]
