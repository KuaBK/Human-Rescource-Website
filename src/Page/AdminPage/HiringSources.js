import React from "react";
import ReactApexChart from "react-apexcharts";

const HiringSources = () => {
    const series = [
        {
            name: "LinkedIn",
            data: [30, 40, 35, 50],
        },
        {
            name: "Company Website",
            data: [20, 30, 40, 30],
        },
        {
            name: "Referrals",
            data: [25, 20, 15, 25],
        },
        {
            name: "Job Portals",
            data: [10, 15, 25, 20],
        },
    ];

    const options = {
        chart: {
            type: "bar",
            stacked: true,
            toolbar: {
                show: true,
            },
        },
        colors: ["#008FFB", "#00E396", "#FEB019", "#FF4560"],
        plotOptions: {
            bar: {
                horizontal: false,
                borderRadius: 5,
                dataLabels: {
                    position: "top",
                },
            },
        },
        xaxis: {
            categories: ["Q1", "Q2", "Q3", "Q4"],
            title: {
                text: "Quarter",
            },
        },
        yaxis: {
            title: {
                text: "Number of Hires",
            },
        },
        legend: {
            position: "top",
            horizontalAlign: "center",
        },
        tooltip: {
            y: {
                formatter: (val) => `${val} Hires`,
            },
        },
    };

    return (
        <div className="col-md-12">
            <div className="card">
                <div className="card-header py-3 d-flex justify-content-between bg-transparent border-bottom-0">
                    <h6 className="mb-0 fw-bold">Top Hiring Sources</h6>
                </div>
                <div className="card-body">
                    <ReactApexChart options={options} series={series} type="bar" height={350} />
                </div>
            </div>
        </div>
    );
};

export default HiringSources;
