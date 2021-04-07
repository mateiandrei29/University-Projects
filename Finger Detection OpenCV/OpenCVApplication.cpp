// OpenCVApplication.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "common.h"
#include "Functions.h"



Mat contur(Mat src)
{
	Mat element1 = getStructuringElement(MORPH_CROSS, Size(5, 5));
	Mat erod;
	Mat newImg(src.rows, src.cols, CV_8UC1);
	newImg.setTo(255);

	erode(src, erod, element1, Point(-1, -1), 1);
	for (int i = 1; i < src.rows - 1; i++)
	{
		for (int j = 1; j < src.cols - 1; j++)
		{
			if (src.at<uchar>(i, j) != erod.at<uchar>(i, j))
			{
				newImg.at<uchar>(i, j) = 0;
			}
		}
	}
	return newImg;

}


Mat region_growing()
{
	Mat src;
	Mat dst;
	Mat invDst;
	int hue_mean = 16;
	int hue_std = 5;
	int k = 2;
	char fname[MAX_PATH];
	Mat matH;


	while (openFileDlg(fname))
	{

		src = imread(fname);
		GaussianBlur(src, src, Size(5, 5), 0, 0);
		resize(src, src, Size(860, 573)); // am dat resize ca sa dea ariile cat de cat la fel, sa fie scalate ca sa pot imparti la o valoare experimentala pt orice mana
		matH = Mat(src.rows, src.cols, CV_8UC1);

		dst = Mat(src.rows, src.cols, CV_8UC1);
		invDst = Mat(src.rows, src.cols, CV_8UC1);
		std::vector <int> di, dj;


		di = { 0, -1, -1, -1,  0 , 1, 1, 1 };
		dj = { 1,  1,  0, -1, -1, -1, 0, 1 };

		int label = 0;
		Mat labels;
		labels = Mat::zeros(src.rows, src.cols, CV_32SC1);

		for (int i = 0; i < src.rows; i++)
		{
			for (int j = 0; j < src.cols; j++)
			{
				Vec3b pixel = src.at<Vec3b>(i, j);
				float r = (float)pixel[2] / 255;
				float g = (float)pixel[1] / 255;
				float b = (float)pixel[0] / 255;

				float M = max(max(r, g), b);
				float m = min(min(r, g), b);
				float C = M - m;

				float H = 0.0;

				//Hue
				if (C != 0)
				{
					if (M == r) H = 60 * (g - b) / C;
					if (M == g) H = 120 + 60 * (b - r) / C;
					if (M == b) H = 240 + 60 * (r - g) / C;
				}
				else
				{
					H = 0;
				}
				if (H < 0) H += 360;

				H = H * 255 / 360;
				matH.at<uchar>(i, j) = H;


				if (((hue_mean - 2.5 * hue_std) <= H &&
					((hue_mean + 2.5 * hue_std) >= H)) &&
					labels.at<int>(i, j) == 0)
				{
					label = 1;
					labels.at<int>(i, j) = label;

					for (int k = 0; k < 8; k++)
					{
						Point neighbor(i + di[k], j + dj[k]);
						if (((hue_mean - 2.5 * hue_std) <= matH.at<uchar>(neighbor.x, neighbor.y) &&
							((hue_mean + 2.5 * hue_std) >= matH.at<uchar>(neighbor.x, neighbor.y))) &&
							labels.at<int>(neighbor.x, neighbor.y) == 0)
						{
							dst.at<uchar>(i, j) = 255;
							label = 1;
							invDst.at<uchar>(i, j) = 0;
						}
					}

				}
				else
				{
					dst.at<uchar>(i, j) = 0;
					invDst.at<uchar>(i, j) = 255;
				}

			}
		}
		Mat element1 = getStructuringElement(MORPH_CROSS, Size(5, 5));
		erode(invDst, invDst, element1, Point(-1, -1), 1);
		erode(invDst, invDst, element1, Point(-1, -1), 1);

		dilate(invDst, invDst, element1, Point(-1, -1), 2);
		dilate(invDst, invDst, element1, Point(-1, -1), 2);

		erode(invDst, invDst, element1, Point(-1, -1), 1);
		erode(invDst, invDst, element1, Point(-1, -1), 1);
		//imshow("original", src);
		//imshow("result", dst);
		//imshow("resultInv", invDst);

		//waitKey(0);
		return invDst;
	}

}

Mat umplere(Mat img) {
	Mat rez = Mat(img.rows, img.cols, CV_8UC1, Scalar(255));
	rez.at<uchar>(img.rows / 2, img.cols / 2) = 0;

	Mat aux; 
	bool ready=false;
	int di[4] = { 0, -1, 0, 1 };
	int dj[4] = { 1, 0, -1, 0 };

	do {
		rez.copyTo(aux);
		//aux = rez.clone();
		ready = true;
		for (int i = 1; i < rez.rows - 1; i++)
			for (int j = 1; j < rez.cols - 1; j++)
				if (rez.at<uchar>(i, j) == 0)
				{
					for (int k = 0; k < 4; k++)
						if (aux.at<uchar>(i + di[k], j + dj[k]) != 0 && img.at<uchar>(i + di[k], j + dj[k]) != 0) {
							aux.at<uchar>(i + di[k], j + dj[k]) = 0;
							ready = false;
						}

				}
		aux.copyTo(rez);
		//rez = aux.clone();
	} while (!ready);

	for (int i = 1; i < img.rows - 1; i++)
		for (int j = 1; j < img.cols - 1; j++)
			if (img.at<uchar>(i, j) == 0)
				rez.at<uchar>(i, j) = 0;

	return rez;
}

void detectieDegete(Mat src)
{
	int area = 0;
	int ri = 0;
	int ci = 0; // pt centru de masa
	int al1 = 0, al2 = 0, al3 = 0; //expresiile pt alungire 
	for (int i = 0; i < src.rows; i++)
	{

		for (int j = 0; j < src.cols; j++)
		{

			if (src.at<uchar>(i, j) == 0)
			{
				area += 1;
				ri += i;
				ci += j;
			}
		}
	}
	//pt centru de masa
	ri /= area;
	ci /= area;

	for (int i = 0; i < src.rows; i++)
	{

		for (int j = 0; j < src.cols; j++)
		{
			if (src.at<uchar>(i, j) == 0)
			{
				al1 += (i - ri) * (j - ci);
				al2 += (j - ci) * (j - ci);
				al3 += (i - ri) * (i - ri);
			}
		}
	}
	float alung = atan2(2 * al1, (al2 - al3)) / 2; // axa de alungire
	cout << area;
	Point p = Point(ci, ri);

	/*Mat element1 = getStructuringElement(MORPH_CROSS, Size(5, 5));
	erode(src, src, element1, Point(-1, -1),1);*/

	Mat cpy = src.clone();


	//line(src, p, p, CV_RGB(255, 255, 255), 5);
	float radius = area / 887;
	circle(src, p, 230, 255, 1, 8, 0); //220



	imshow("linie", src);

	



	absdiff(cpy, src, cpy);



	imshow("diff", cpy);



	std::vector<std::vector<cv::Point> > contours;
	findContours(cpy, contours, RETR_EXTERNAL, CV_CHAIN_APPROX_SIMPLE);




	int numar_intersectii = contours.size();
	cout << "Aceasta mana are " << numar_intersectii - 1 << " degete";


	/*Mat distance; distance transform something
	Mat binaryImg;

	distanceTransform(src, distance, DIST_L2, 5, CV_32F);
	threshold(src, src, 200, 255, THRESH_BINARY);

	float maxDist = 0;
	int x, y;
	for (int i = 0; i < distance.rows; i++) {
		for (int j = 0; j < distance.cols; j++) {
			float dist = distance.at<float>(i, j);
			if (maxDist < dist) {
				x = j; y = i;
				maxDist = dist;
			}
		}
	}

	circle(src, Point(x, y), 3, Scalar(255, 0, 255), 2, 8, 0);
	imshow("palmCenter", src);*/


}

int main()
{

	Mat manaSegmentata = region_growing();
	Mat conturMana = contur(manaSegmentata);
	//Mat manaUmpluta = umplere(conturMana);
	imshow("Segmentare", manaSegmentata);
	imshow("Contur", conturMana);
	//imshow("Umplere", manaUmpluta);
	detectieDegete(manaSegmentata);
	waitKey(0);

	return 0;
}