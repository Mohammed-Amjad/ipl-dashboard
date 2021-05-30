import React from 'react';
import { PieChart } from 'react-minimal-pie-chart';
import './WinPercentPieChart.scss'

export const WinPercentPieChart = ({winPercent}) => {
    return (
        <div className="WinPercentPieChart">
            <h3>Winning percentage</h3>
            <PieChart
                data={[{ value: winPercent, color: 'rgb(159, 240, 106)' }]}
                totalValue={100}
                lineWidth={30}
                label={({ dataEntry }) => dataEntry.value + '%'}
                labelStyle={{
                    fontSize: '25px',
                    fontFamily: 'sans-serif',
                    fill: 'rgb(159, 240, 106)'
                }}
                labelPosition={0}
            />
        </div>
    );
}